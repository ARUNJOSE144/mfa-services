package com.sixdee.magik.services.adaptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sixdee.magik.services.controller.ActionFileDownloadRestContoller;
import com.sixdee.magik.services.model.ActionFileTO;
import com.sixdee.magik.services.service.ActionFileService;

@Component
public class StatusRequestProcessBO {

	@Autowired
	ActionFileService actionFileService;

	@SuppressWarnings("unused")
	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	private Session session = null;

	private Logger logger = Logger.getLogger(StatusRequestProcessBO.class);

	public void storeStatusInfo(FileDownlaodTO reqDTO) {

		try {

			if (reqDTO != null) {
				System.out.println("11111---------------> Going to Start process  ");
				logger.info("<<<---------------> Going to Start process  ");

				ArrayList<Detail> DetailsList = (ArrayList<Detail>) reqDTO.getData().getLists();
				Detail detail = DetailsList.get(0);
				ArrayList<Param> params = (ArrayList<Param>) detail.getParam();

				System.out.println("params :::  " + params.toString());

				List<ActionFileTO> actionFileTOs = new ArrayList<ActionFileTO>();
				ActionFileTO actionFileTO = new ActionFileTO();
				for (Param param : params) {
					if (param.getName() != null) {
						if (param.getName().trim().equals("ScheduleId")) {
							if (param.getValue() != null) {
								actionFileTO.setScheduledId(Integer.parseInt(param.getValue()));
								actionFileTOs.add(actionFileTO);
							}
						}
						if (param.getName().trim().equals("NODE_NAME")) {
							if (param.getValue() != null) {
								actionFileTO.setNodeName((param.getValue()));
								actionFileTOs.add(actionFileTO);
							}
						}
						if (param.getName().trim().equals("EXECUTION_LEVEL")) {
							if (param.getValue() != null) {
								actionFileTO.setExecutionLevel(Integer.parseInt(param.getValue()));
								actionFileTOs.add(actionFileTO);
							}
						}
						if (param.getName().trim().equals("EXECUTION_TIME")) {
							if (param.getValue() != null) {
								actionFileTO.setExecutionTime(new Date());
								actionFileTOs.add(actionFileTO);
							}
						}
						if (param.getName() != null && !param.getName().trim().equals("ScheduleId")
								&& !param.getName().trim().equals("NODE_NAME")
								&& !param.getName().trim().equals("EXECUTION_LEVEL")
								&& !param.getName().trim().equals("EXECUTION_TIME")) {
							System.out.println("Inside param Name>>>" + param.getName());
							actionFileTO.setActionId(param.getName());
							actionFileTO.setActionFileName(param.getValue());
							if (param.getLineNumber() != null) {
								// actionFileTO.setCounts(param.getCount());
								System.out.println("line Number  ::   " + param.getLineNumber());
								actionFileTO.setCounts(param.getLineNumber());
							}
							actionFileTOs.add(actionFileTO);
						}
						/// actionFileTOs.add(actionFileTO);
					}

				}
				System.out.println("actionFileTOs  full  ::   " + actionFileTOs.toString());
				System.out.println("actionFileTOs   ::   " + actionFileTOs.get(0).toString());
				System.out.println("actionFileService    ::   " + actionFileService);
				ActionFileDownloadRestContoller.actService.saveFileDetails(actionFileTOs);
				// actionFileService.saveFileDetailsData(actionFileTOs.get(0));
				System.out.println("after calling");

			}
		} catch (Exception e) {
			System.out.println("in exception>>>BO");
			e.printStackTrace();
		} finally {

			System.out.println("in finally BO");
		}
	}

}
