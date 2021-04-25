package com.sixdee.magik.services.adaptor;

import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.sixdee.magik.services.service.ActionFileService;

@Configurable
public class ReqProcWorkerThread extends Thread {

	@Autowired
	ActionFileService actionFileService;

	Logger logger = Logger.getLogger(ReqProcWorkerThread.class);
	private LinkedList<FileDownlaodTO> taskList;
	private volatile boolean flag = true;
	private String threadName = null;

	public ReqProcWorkerThread(LinkedList<FileDownlaodTO> taskList1, int threadNo) {
		super("ReqProcWorkerThread_" + threadNo);
		this.threadName = "ReqProcWorkerThread_" + threadNo;
		this.taskList = taskList1;

		System.out.println(
				" --------------------> in  ReqProcWorkerThread  Assiging Thread and task details are  " + taskList1);
	}

	public void setFlagFalse() {
		flag = false;
	}

	public void stopThreadProc() {
		flag = false;
		this.interrupt();
	}

	public void run() {
		FileDownlaodTO reqDTO = null;

		while (flag) {
			synchronized (taskList) {
				while (true) {
					if (!taskList.isEmpty()) {
						reqDTO = taskList.removeFirst();
						break;
					} else {
						try {
							taskList.wait();
						} catch (InterruptedException e) {
							break;
						}
					}
				}
			}
			StatusRequestProcessBO statusReqProcess = null;
			try {
				if (reqDTO != null) {

					logger.info("---------------> Going to Start process  ");

					statusReqProcess = new StatusRequestProcessBO();
					statusReqProcess.storeStatusInfo(reqDTO);
				}
			} catch (Exception e) {
				logger.error("In " + threadName + " run() Exception for TransID :" + reqDTO.getReqTxnId());
			} finally {
				statusReqProcess = null;
				reqDTO = null;
			}

			/*
			 * try { if (reqDTO != null) {
			 * System.out.println("<<<---------------> Going to Start process  ");
			 * logger.info("<<<---------------> Going to Start process  "); ArrayList<Param>
			 * params =(ArrayList<Param>) reqDTO.getData().getDetailReq().getLists();
			 * 
			 * List<ActionFileTO> actionFileTOs = new ArrayList<ActionFileTO>(); for (Param
			 * param : params) { System.out.println("param Name>>>"+param.getName());
			 * if(param.getName() != null && !param.getName().trim().equals("ScheduleId") &&
			 * !param.getName().trim().equals("NODE_NAME") &&
			 * !param.getName().trim().equals("EXECUTION_LEVEL") &&
			 * !param.getName().trim().equals("EXECUTION_TIME")){
			 * System.out.println("Inside param Name>>>"+param.getName()); ActionFileTO
			 * actionFileTO = new ActionFileTO(); actionFileTO.setActionId(param.getName());
			 * actionFileTO.setActionFileName(param.getValue());
			 * actionFileTO.setCounts(param.getCount()); actionFileTOs.add(actionFileTO); }
			 * } System.out.println("Size.>>"+actionFileTOs.size()); for (ActionFileTO
			 * actionFileTO : actionFileTOs) { for (Param param : params) {
			 * if(param.getName().trim().equals("ScheduleId"))
			 * actionFileTO.setScheduledId(param.getValue());
			 * if(param.getName().trim().equals("NODE_NAME"))
			 * actionFileTO.setNodeName(param.getValue());
			 * if(param.getName().trim().equals("EXECUTION_LEVEL"))
			 * actionFileTO.setExecutionLevel(Integer.parseInt(param.getValue()));
			 * if(param.getName().trim().equals("EXECUTION_TIME")){ DateFormat sdf = new
			 * SimpleDateFormat("ddMMyyyy HH:mm:ss"); //Date date
			 * =sdf.parse(param.getName().trim()); //actionFileTO.setExecutionTime(new
			 * Date()); } } System.out.println("Before>>>"+actionFileTO); }
			 * 
			 * 
			 * //ActionFileService actionFileService = (ActionFileService) new
			 * ActionsServiceImpl(); System.out.println("before calling");
			 * actionFileService.saveFileDetails(actionFileTOs);
			 * System.out.println("a calling");
			 * 
			 * } } catch(Exception e) {
			 * System.out.println("In "+threadName+" run() Exception for TransID :"+
			 * reqDTO.getReq_tran_id());
			 * logger.error("In "+threadName+" run() Exception for TransID :"+
			 * reqDTO.getReq_tran_id()); } finally { //statusReqProcess=null;
			 * System.out.println("In finally"); reqDTO = null; }
			 */
		}
	}

	/**
	 * @return Returns the threadName.
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * @param threadName The threadName to set.
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
