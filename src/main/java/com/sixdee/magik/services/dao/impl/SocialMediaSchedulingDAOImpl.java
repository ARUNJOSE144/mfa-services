package com.sixdee.magik.services.dao.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixdee.magik.services.dao.SocialMediaSchedulingDAO;
import com.sixdee.magik.services.model.IdNameDTO;
import com.sixdee.magik.services.model.SocialMediaScheduleMappingDTO;
import com.sixdee.magik.services.model.SocialMediaScheduleMasterDTO;
import com.sixdee.magik.services.model.TelegramBodyDTO;
import com.sixdee.magik.services.model.TelegramBotCommandModel;
import com.sixdee.magik.services.model.TelegramBotCommands;
import com.sixdee.magik.services.model.TelegramBotConfig;
import com.sixdee.magik.services.model.TelegramChatDetailsDTO;
import com.sixdee.magik.services.model.TelegramDTO;
import com.sixdee.magik.services.model.TelegramSendMessageDTO;
import com.sixdee.magik.services.threads.SocialMediaSchedulerThread;

@Repository
public class SocialMediaSchedulingDAOImpl implements SocialMediaSchedulingDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	SocialMediaSchedulerThread socialMediaThread;

	@Autowired
	private Environment env;

	public SocialMediaScheduleMasterDTO createScheduling(SocialMediaScheduleMasterDTO request) {

		SocialMediaScheduleMasterDTO response = null;
		Session session = null;

		try {

			response = new SocialMediaScheduleMasterDTO();

			session = sessionFactory.getCurrentSession();
			session.save(request);

			if (request.getData() != null && request.getData().size() > 0) {
				for (IdNameDTO dto : request.getData()) {
					SocialMediaScheduleMappingDTO mappingDto = new SocialMediaScheduleMappingDTO(dto.getId(),
							dto.getName(), dto.getId(), request);
					session.save(mappingDto);
				}
			}

			response.setStatus("SC0000");
			response.setStatusDesc("SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("FAILED");

		}

		return response;
	}

	public SocialMediaScheduleMasterDTO updateScheduling(SocialMediaScheduleMasterDTO request) {

		SocialMediaScheduleMasterDTO response = null;
		Session session = null;
		Query query = null;

		try {

			response = new SocialMediaScheduleMasterDTO();

			session = sessionFactory.getCurrentSession();
			SocialMediaScheduleMasterDTO master = session.get(SocialMediaScheduleMasterDTO.class, request.getId());
			master.setSchedulingType(request.getSchedulingType());
			session.saveOrUpdate(master);

			query = session
					.createSQLQuery("DELETE FROM SOCIAL_MEDIA_SCHEDULE_MAPPING WHERE SCHEDULE_ID=" + request.getId());
			query.executeUpdate();

			if (request.getData() != null && request.getData().size() > 0) {
				for (IdNameDTO dto : request.getData()) {
					SocialMediaScheduleMappingDTO mappingDto = new SocialMediaScheduleMappingDTO(dto.getId(),
							dto.getName(), dto.getId(), request);
					session.save(mappingDto);
				}
			}
			response.setStatus("SC0000");
			response.setStatusDesc("SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("FAILED");

		}

		return response;
	}

	public SocialMediaScheduleMasterDTO deleteScheduling(SocialMediaScheduleMasterDTO request) {

		SocialMediaScheduleMasterDTO response = null;
		Session session = null;

		try {

			response = new SocialMediaScheduleMasterDTO();

			session = sessionFactory.getCurrentSession();
			session.delete(request);

			response.setStatus("SC0000");
			response.setStatusDesc("SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("FAILED");

		}

		return response;
	}

	@SuppressWarnings("unchecked")
	public SocialMediaScheduleMasterDTO getSchedulingDetails(String campaignId) {

		SocialMediaScheduleMasterDTO response = null;
		Session session = null;
		List<IdNameDTO> idNameDto = null;
		List<SocialMediaScheduleMasterDTO> respList = null;

		try {

			response = new SocialMediaScheduleMasterDTO();

			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(SocialMediaScheduleMasterDTO.class);
			if (campaignId != null && !campaignId.equals("") && !campaignId.equals("0")) {
				cr.add(Restrictions.eq("campaignId", Integer.parseInt(campaignId)));
			}

			List<SocialMediaScheduleMasterDTO> masterList = cr.list();

			respList = new ArrayList<>();
			for (SocialMediaScheduleMasterDTO dto : masterList) {

				if (dto.getMappingDetails() != null && dto.getMappingDetails().size() > 0) {
					idNameDto = new ArrayList<>();
					for (SocialMediaScheduleMappingDTO mapDto : dto.getMappingDetails()) {
						IdNameDTO tag = new IdNameDTO();
						tag.setId(mapDto.getIdParam());
						tag.setName(mapDto.getValueParam());
						idNameDto.add(tag);
					}
					dto.setData(idNameDto);
					dto.setMappingDetails(null);
				}

				respList.add(dto);

			}

			response.setListData(respList);
			response.setStatus("SC0000");
			response.setStatusDesc("SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("FAILED");

		}

		return response;

	}

	@SuppressWarnings("unchecked")
	public void chatWithTelegramBot(TelegramBodyDTO request, String botId) {

		String chatId = null, reqCommand = null, respMessage = null, url = "", fileName = null, mediaType = null,
				responseType = null;

		int lastMessageId = 0, messageId = 0;

		Session session = null;

		try {

			System.out.println("bot : " + botId + " | request : " + request);

			if (request != null) {

				session = sessionFactory.getCurrentSession();

				chatId = String.valueOf(request.getMessage().getChat().getId());
				reqCommand = request.getMessage().getText();
				url = env.getProperty("telegram.url") + botId + ":" + socialMediaThread.botDetails.get(botId);

				botId = socialMediaThread.botIDId.get(botId);

				System.out.println("Bot Id : " + botId);

				List<TelegramChatDetailsDTO> chatList = session.createCriteria(TelegramChatDetailsDTO.class)
						.add(Restrictions.eq("chatId", chatId)).add(Restrictions.eq("botId", botId)).setMaxResults(1)
						.addOrder(Order.desc("id")).list();

				if (chatList != null && chatList.size() > 0) {

					for (TelegramChatDetailsDTO chatDto : chatList) {

						lastMessageId = chatDto.getMessageId();
						System.out.println("Last Message Id : " + lastMessageId);
					}

					if (lastMessageId != 0) {
						List<TelegramDTO> childList = session.createCriteria(TelegramDTO.class)
								.add(Restrictions.eq("parentId", lastMessageId))
								.add(Restrictions.eq("command", reqCommand)).list();

						System.out.println("inside lastMessageId ");

						if (childList != null && childList.size() > 0) {

							System.out.println("inside childList ");

							for (TelegramDTO dto : childList) {
								responseType = dto.getResponseType();
								messageId = dto.getId();
								fileName = env.getProperty("file.upload.path") + dto.getFileId();
								respMessage = dto.getMessage();

								if (responseType.equalsIgnoreCase("option")) {
									mediaType = "text";
									respMessage = getOptionData(dto.getId(), session, dto.getBotId());
								}

							}
						} else {

							List<TelegramDTO> messageDetails = session.createCriteria(TelegramDTO.class)
									.add(Restrictions.eq("command", reqCommand))
									.add(Restrictions.eq("commandStatus", "active")).add(Restrictions.eq("parentId", 0))
									.list();
							System.out.println("outside messageDetails ");

							if (messageDetails != null && messageDetails.size() > 0) {

								System.out.println("inside messageDetails ");

								for (TelegramDTO dto : messageDetails) {

									messageId = dto.getId();
									fileName = env.getProperty("file.upload.path") + dto.getFileId();
									respMessage = dto.getMessage();
									responseType = dto.getResponseType();

									if (responseType.equalsIgnoreCase("option")) {
										mediaType = "text";
										respMessage = getOptionData(dto.getId(), session, dto.getBotId());
									}

								}

							} else {
								System.out.println("inside first else ");

								TelegramDTO oldMessage = session.get(TelegramDTO.class, lastMessageId);
								System.out.println("OldMessage Parent " + oldMessage.getParentId());

								List<TelegramDTO> oldNodeDetails = session.createCriteria(TelegramDTO.class)
										.add(Restrictions.eq("command", reqCommand))
										.add(Restrictions.eq("commandStatus", "active"))
										.add(Restrictions.eq("parentId", oldMessage.getParentId())).list();

								if (oldNodeDetails != null && oldNodeDetails.size() > 0) {

									System.out.println("inside old node Details");

									for (TelegramDTO dto : oldNodeDetails) {

										messageId = dto.getId();
										fileName = env.getProperty("file.upload.path") + dto.getFileId();
										respMessage = dto.getMessage();
										responseType = dto.getResponseType();

										if (responseType.equalsIgnoreCase("option")) {
											mediaType = "text";
											respMessage = getOptionData(dto.getId(), session, dto.getBotId());
										}

									}

								} else {

									respMessage = "Command not found";
									messageId = 0;
									mediaType = "text";
									responseType = "text";

								}

							}

						}

					} else {

						System.out.println("inside second else ");

						List<TelegramDTO> messageDetails = session.createCriteria(TelegramDTO.class)
								.add(Restrictions.eq("command", reqCommand))
								.add(Restrictions.eq("commandStatus", "active")).add(Restrictions.eq("parentId", 0))
								.list();

						if (messageDetails != null && messageDetails.size() > 0) {
							System.out.println("inside second else messageDetails");

							for (TelegramDTO dto : messageDetails) {

								messageId = dto.getId();
								fileName = env.getProperty("file.upload.path") + dto.getFileId();
								respMessage = dto.getMessage();
								responseType = dto.getResponseType();

								if (responseType.equalsIgnoreCase("option")) {
									mediaType = "text";
									respMessage = getOptionData(dto.getId(), session, dto.getBotId());
								}

							}

						} else {
							System.out.println("inside third else ");
							respMessage = "Command not found";
							messageId = 0;
							mediaType = "text";
							responseType = "text";

						}

					}

				} else {
					System.out.println("inside fourth else ");

					List<TelegramDTO> messageDetails = session.createCriteria(TelegramDTO.class)
							.add(Restrictions.eq("command", reqCommand)).add(Restrictions.eq("commandStatus", "active"))
							.add(Restrictions.eq("parentId", 0)).list();

					if (messageDetails != null && messageDetails.size() > 0) {

						System.out.println("inside fourth else messageDetails");

						for (TelegramDTO dto : messageDetails) {

							messageId = dto.getId();
							fileName = env.getProperty("file.upload.path") + dto.getFileId();
							respMessage = dto.getMessage();
							responseType = dto.getResponseType();

							if (responseType.equalsIgnoreCase("option")) {
								mediaType = "text";
								respMessage = getOptionData(dto.getId(), session, dto.getBotId());
							}

						}

					} else {
						System.out.println("inside fifth else");

						respMessage = "Command not found";
						messageId = 0;
						mediaType = "text";
						responseType = "text";

					}

				}

				if (responseType.equalsIgnoreCase("text")) {
					mediaType = "text";
				} else if (responseType.equalsIgnoreCase("image")) {
					url += "/sendPhoto";
					mediaType = "photo";

				} else if (responseType.equalsIgnoreCase("audio")) {
					url += "/sendAudio";
					mediaType = "audio";

				} else if (responseType.equalsIgnoreCase("video")) {
					url += "/sendVideo";
					mediaType = "video";

				} else if (responseType.equalsIgnoreCase("animation")) {
					url += "/sendAnimation";
					mediaType = "animation";

				}

				if (!mediaType.equals("text")) {

					CallThirdPartyUrl.callPostFile(url, fileName, chatId, mediaType, respMessage);

				} else {

					url += "/sendMessage?chat_id=" + chatId + "&text="
							+ URLEncoder.encode(respMessage, StandardCharsets.UTF_8.toString());

					CallThirdPartyUrl.callGet(url);
				}

				TelegramChatDetailsDTO chatDetailsDTO = new TelegramChatDetailsDTO(
						socialMediaThread.botNamings.get(botId), botId, chatId,
						request.getMessage().getChat().getFirst_name(), request.getMessage().getChat().getLast_name(),
						reqCommand, respMessage, messageId);

				session.save(chatDetailsDTO);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private String getOptionData(int id, Session session, int botId) {

		String response = "";

		try {

			List<TelegramDTO> tempList = session.createCriteria(TelegramDTO.class).add(Restrictions.eq("parentId", id))
					.add(Restrictions.eq("commandStatus", "active")).add(Restrictions.eq("botId", botId)).list();

			if (tempList != null && tempList.size() > 0) {
				for (TelegramDTO dto : tempList) {
					response += dto.getCommand() + " \n";
				}
			}

			System.out.println("option response : " + response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return response;
	}

	public String sendMessageToChat(TelegramSendMessageDTO request) {

		System.out.println("Request : " + request);

		String url = "", response = null, fileName = null, mediaType = null;
		Session session = null;

		try {
			if (request.getChatId() != null) {

				session = sessionFactory.getCurrentSession();
				TelegramBotConfig botDetails = session.get(TelegramBotConfig.class, request.getBotId());
				request.setBotName(botDetails.getBotName());

				url = env.getProperty("telegram.url") + botDetails.getBotId() + ":" + botDetails.getBotKey();

				if (request.getMediaType().equalsIgnoreCase("TEXT")) {

					url += "/sendMessage?chat_id=" + request.getChatId() + "&text="
							+ URLEncoder.encode(request.getMessage(), StandardCharsets.UTF_8.toString());

					response = CallThirdPartyUrl.callGet(url);

				} else {

					if (request.getFileId() != null && !request.getFileId().equals("")) {

						fileName = env.getProperty("file.upload.path") + request.getFileId();

						if (request.getMediaType().equalsIgnoreCase("IMAGE")) {
							url += "/sendPhoto";
							mediaType = "photo";
						} else if (request.getMediaType().equalsIgnoreCase("AUDIO")) {
							url += "/sendAudio";
							mediaType = "audio";
						} else if (request.getMediaType().equalsIgnoreCase("VIDEO")) {
							url += "/sendVideo";
							mediaType = "video";
						} else if (request.getMediaType().equalsIgnoreCase("ANIMATION")) {
							url += "/sendAnimation";
							mediaType = "animation";
						}

						response = CallThirdPartyUrl.callPostFile(url, fileName, request.getChatId(), mediaType,
								request.getMessage());
					}

				}

				if (response.equalsIgnoreCase("FAILED")) {
					request.setStatus("Broadcasting failed from Telegram");
				} else {
					request.setStatus("Broadcasting Success");
				}

				session.save(request);

				return "SC0000";

			} else {
				return "SC0001";
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "SC0001";

	}

	public TelegramDTO configureBotCommands(TelegramDTO request) {

		System.out.println("Request : " + request);

		Session session = null;
		TelegramDTO respDto = null;
		int botId = 0;
		String response = "SC0000";

		try {

			respDto = new TelegramDTO();
			session = sessionFactory.getCurrentSession();

			if (request.getCommandsList() != null && request.getCommandsList().size() > 0) {
				for (TelegramDTO dto : request.getCommandsList()) {
					dto.setCommand("/" + dto.getCommand().toLowerCase());
					dto.setBotId(request.getBotId());
					botId = request.getBotId();
					dto.setParentId(0);
					session.save(dto);

					if (dto.getResponseType() != null && !dto.getResponseType().equals("")
							&& dto.getResponseType().equalsIgnoreCase("option")) {
						if (dto.getChildren() > 0) {
							response = saveCommandChildren(request.getBotId(), dto.getId(), dto.getCommandsList());
						}

					}

				}
			}

			if (response.equals("SC0000")) {
				respDto.setStatus("SC0000");
				respDto.setStatusDesc("SUCCESS");
			} else {
				respDto.setStatus("SC0001");
				respDto.setStatusDesc("FAILED");
			}

			// updating bot commands
			updateTelegramBotCommads(botId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respDto.setStatus("SC0001");
			respDto.setStatusDesc("FAILED");
		}

		return respDto;
	}

	private String saveCommandChildren(int botId, int id, List<TelegramDTO> list) {

		Session session = null;
		String response = "SC0000";

		try {
			session = sessionFactory.getCurrentSession();
			if (list != null && list.size() > 0) {
				for (TelegramDTO dto : list) {
					dto.setCommand("/" + dto.getCommand().toLowerCase());
					dto.setBotId(botId);
					dto.setParentId(id);
					session.save(dto);

					if (dto.getResponseType() != null && !dto.getResponseType().equals("")
							&& dto.getResponseType().equalsIgnoreCase("option")) {
						if (dto.getChildren() > 0) {
							response = saveCommandChildren(dto.getBotId(), dto.getId(), dto.getCommandsList());
						}

					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = "SC0001";
		}

		return response;

	}

	@SuppressWarnings("unchecked")
	@Async
	private void updateTelegramBotCommads(int botId) {

		System.out.println("Updating bot commands for botId : " + botId);
		Session session = null;
		String url = null;
		ObjectMapper mapper = null;

		try {
			session = sessionFactory.getCurrentSession();
			List<TelegramDTO> commandsList = session.createCriteria(TelegramDTO.class)
					.add(Restrictions.eq("botId", botId)).add(Restrictions.eq("commandStatus", "active"))
					.add(Restrictions.eq("parentId", 0)).add(Restrictions.not(Restrictions.eq("command", "error_msg")))
					.list();

			List<TelegramBotConfig> botList = session.createCriteria(TelegramBotConfig.class)
					.add(Restrictions.eq("id", botId)).list();

			for (TelegramBotConfig botDto : botList) {

				TelegramBotCommandModel reqJson = new TelegramBotCommandModel();

				List<TelegramBotCommands> reqCommandsList = new ArrayList<>();
				mapper = new ObjectMapper();

				for (TelegramDTO commandDto : commandsList) {
					TelegramBotCommands comm = new TelegramBotCommands();
					comm.setCommand(commandDto.getCommand().toLowerCase());
					comm.setDescription(commandDto.getCommand());
					reqCommandsList.add(comm);
				}
				reqJson.setCommands(reqCommandsList);

				url = env.getProperty("telegram.url") + botDto.getBotId() + ":" + botDto.getBotKey() + "/setMyCommands";
				String requestJson = mapper.writeValueAsString(reqJson);
				CallThirdPartyUrl.callPost(url, requestJson, "JSON");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public TelegramBotConfig manageTelegramBot(TelegramBotConfig request) {

		Session session = null;
		TelegramBotConfig response = null;

		try {

			response = new TelegramBotConfig();

			if (request != null && request.getBotId() != null && request.getBotKey() != null
					&& request.getBotName() != null) {

				session = sessionFactory.getCurrentSession();

				if (request.getId() > 0) {
					TelegramBotConfig conf = session.get(TelegramBotConfig.class, request.getId());
					if (conf.getLocked() == 1) {
						response.setStatus("SC0002");
						response.setStatusDesc("Locked Bot. Can not be Modified");

					} else {
						conf.setBotId(request.getBotId());
						conf.setBotKey(request.getBotKey());
						conf.setBotName(request.getBotName());
						session.saveOrUpdate(conf);
						response.setStatus("SC0000");
						response.setStatusDesc("Updation Success");
					}
				} else {
					session.save(request);
					response.setStatus("SC0000");
					response.setStatusDesc("Insertion Success");
				}
			} else {
				response.setStatus("SC0001");
				response.setStatusDesc("Invalid Request");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("Failed");
		}

		return response;
	}

	public TelegramBotConfig deleteTelegramBot(TelegramBotConfig request) {

		Session session = null;
		TelegramBotConfig response = null;

		try {

			response = new TelegramBotConfig();

			if (request != null && request.getId() > 0) {
				session = sessionFactory.getCurrentSession();

				TelegramBotConfig conf = session.get(TelegramBotConfig.class, request.getId());
				if (conf.getLocked() == 1) {
					response.setStatus("SC0002");
					response.setStatusDesc("Locked Bot. Can not be Deleted");

				} else {
					session.delete(conf);
					response.setStatus("SC0000");
					response.setStatusDesc("Deletion Success");
				}

			} else {
				response.setStatus("SC0001");
				response.setStatusDesc("Invalid Request");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("Failed");
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	public TelegramBotConfig getTelegramBotList() {
		Session session = null;
		TelegramBotConfig response = null;

		try {
			response = new TelegramBotConfig();
			session = sessionFactory.getCurrentSession();

			response.setBotList(session.createCriteria(TelegramBotConfig.class).list());
			response.setStatus("SC0000");
			response.setStatusDesc("Success");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("Failed");
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	public TelegramSendMessageDTO getBroadcastDetails() {

		Session session = null;
		TelegramSendMessageDTO response = null;

		try {
			session = sessionFactory.getCurrentSession();
			response = new TelegramSendMessageDTO();

			response.setBroadcastList(
					session.createCriteria(TelegramSendMessageDTO.class).addOrder(Order.desc("id")).list());
			response.setStatusDesc("SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatusDesc("FAILED");
		}

		return response;
	}

	public TelegramDTO updateBotCommands(TelegramDTO request) {

		Session session = null;
		TelegramDTO respDto = null;
		String response = "SC0000";

		try {

			respDto = new TelegramDTO();
			session = sessionFactory.getCurrentSession();

			for (TelegramDTO dto : request.getCommandsList()) {
				if (dto.getId() > 0) {
					dto.setBotId(request.getBotId());
					dto.setCommand("/" + dto.getCommand().toLowerCase());
					session.saveOrUpdate(dto);

					if (dto.getResponseType() != null && !dto.getResponseType().equals("")
							&& dto.getResponseType().equalsIgnoreCase("option")) {
						if (dto.getChildren() > 0) {
							response = updateCommandChildren(dto.getBotId(), dto.getCommandsList());
						}

					}

				}
			}

			if (response.equalsIgnoreCase("SC0000")) {
				respDto.setStatus("SC0000");
				respDto.setStatusDesc("SUCCESS");
			} else {
				respDto.setStatus("SC0001");
				respDto.setStatusDesc("FAILED");
			}

		} catch (Exception e) {
			// TODO: handle exception
			respDto.setStatus("SC0001");
			respDto.setStatusDesc("FAILED");
			e.printStackTrace();
		}

		return respDto;
	}

	private String updateCommandChildren(int botId, List<TelegramDTO> list) {

		Session session = null;
		String response = "SC0000";

		try {
			session = sessionFactory.getCurrentSession();
			if (list != null && list.size() > 0) {
				for (TelegramDTO dto : list) {
					dto.setCommand("/" + dto.getCommand().toLowerCase());
					dto.setBotId(botId);
					session.saveOrUpdate(dto);

					if (dto.getResponseType() != null && !dto.getResponseType().equals("")
							&& dto.getResponseType().equalsIgnoreCase("option")) {
						if (dto.getChildren() > 0) {
							response = updateCommandChildren(dto.getBotId(), dto.getCommandsList());
						}

					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = "SC0001";
		}

		return response;

	}

	@SuppressWarnings("unchecked")
	public TelegramDTO getBotCommands() {

		Session session = null;
		TelegramDTO response = null;
		List<TelegramDTO> telList = null;

		try {

			response = new TelegramDTO();
			session = sessionFactory.getCurrentSession();

			List<TelegramDTO> tempList = session.createCriteria(TelegramDTO.class).addOrder(Order.desc("id")).list();

			if (tempList != null && tempList.size() > 0) {

				telList = new ArrayList<>();

				for (TelegramDTO dto : tempList) {

					if (dto.getParentId() == 0) {

						TelegramDTO tempDto = new TelegramDTO();
						tempDto.setBotId(dto.getBotId());
						tempDto.setChildren(dto.getChildren());
						tempDto.setId(dto.getId());
						tempDto.setCommand(dto.getCommand());
						tempDto.setCommandStatus(dto.getCommandStatus());
						tempDto.setFileId(dto.getFileId());
						tempDto.setParentId(dto.getParentId());
						tempDto.setResponseType(dto.getResponseType());
						tempDto.setMessage(dto.getMessage());

						if (dto.getResponseType().equalsIgnoreCase("option")) {
							if (dto.getChildren() > 0) {
								List<TelegramDTO> arrayList = getChildData(dto.getId());
								tempDto.setCommandsList(arrayList);
							}
						}
						telList.add(tempDto);
					}

				}

				response.setCommandsList(telList);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("FAILED");
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	private List<TelegramDTO> getChildData(int id) {

		Session session = null;
		List<TelegramDTO> respList = null;

		try {

			session = sessionFactory.getCurrentSession();

			List<TelegramDTO> tempList = session.createCriteria(TelegramDTO.class).add(Restrictions.eq("parentId", id))
					.list();

			if (tempList != null && tempList.size() > 0) {

				respList = new ArrayList<>();

				for (TelegramDTO dto : tempList) {

					TelegramDTO tempDto = new TelegramDTO();
					tempDto.setBotId(dto.getBotId());
					tempDto.setChildren(dto.getChildren());
					tempDto.setId(dto.getId());
					tempDto.setCommand(dto.getCommand());
					tempDto.setCommandStatus(dto.getCommandStatus());
					tempDto.setFileId(dto.getFileId());
					tempDto.setParentId(dto.getParentId());
					tempDto.setResponseType(dto.getResponseType());
					tempDto.setMessage(dto.getMessage());

					if (dto.getResponseType().equalsIgnoreCase("option")) {
						if (dto.getChildren() > 0) {
							List<TelegramDTO> arrayList = getChildData(dto.getId());
							tempDto.setCommandsList(arrayList);
						}
					}
					respList.add(tempDto);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return respList;
	}

	@SuppressWarnings("unchecked")
	public TelegramDTO deleteBotCommands(String commandId) {

		Session session = null;
		TelegramDTO response = null;
		String resp = "SC0000";

		try {
			response = new TelegramDTO();

			session = sessionFactory.getCurrentSession();
			TelegramDTO dto = session.get(TelegramDTO.class, Integer.parseInt(commandId));
			if (dto.getChildren() == 0) {
				session.delete(dto);
			} else {
				session.delete(dto);

				List<TelegramDTO> tempList = session.createCriteria(TelegramDTO.class)
						.add(Restrictions.eq("parentId", dto.getId())).list();

				for (TelegramDTO tempDto : tempList) {

					session.delete(tempDto);

					if (tempDto.getChildren() > 0) {
						resp = deleteChildren(tempDto.getId(), session);
					}

				}

			}

			if (resp.equals("SC0001")) {
				response.setStatus("SC0001");
				response.setStatusDesc("FAILED");
			} else {
				response.setStatus("SC0000");
				response.setStatusDesc("SUCCESS");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("FAILED");
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	private String deleteChildren(int id, Session session) {

		String response = "SC0000";

		try {

			List<TelegramDTO> tempList = session.createCriteria(TelegramDTO.class).add(Restrictions.eq("parentId", id))
					.list();

			for (TelegramDTO tempDto : tempList) {

				session.delete(tempDto);

				if (tempDto.getChildren() > 0) {
					response = deleteChildren(tempDto.getId(), session);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = "SC0001";
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public TelegramDTO uniqueBotCommands(String command, String botId) {

		Session session = null;
		TelegramDTO response = null;

		try {
			response = new TelegramDTO();
			session = sessionFactory.getCurrentSession();

			List<TelegramDTO> tempList = session.createCriteria(TelegramDTO.class)
					.add(Restrictions.eq("command", command.toLowerCase()))
					.add(Restrictions.eq("botId", Integer.parseInt(botId))).list();

			if (tempList != null && tempList.size() > 0) {
				response.setStatus("SC0001");
				response.setStatusDesc("Command already configured");
			} else {
				response.setStatus("SC0000");
				response.setStatusDesc("Command available to configure");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("SC0001");
			response.setStatusDesc("Command already configured");
		}

		return response;
	}

}
