package com.sixdee.magik.services.adaptor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sixdee.magik.services.exception.CommonException;
import com.sixdee.magik.services.service.ActionFileService;
import com.sixdee.magik.services.util.RuleStatusParser;
import com.thoughtworks.xstream.XStreamException;

@Configurable
public class ActionFileAdapter  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ActionFileService actService = null;
	@Autowired
	ActionFileService actionFileService;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in do gettt");
		execute(req,resp);
	}
	
	 /**
     * Default constructor. 
     */
	public ActionFileAdapter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in posttt");
		execute(req,resp);
	}
	
	private void execute(HttpServletRequest req, HttpServletResponse resp) {
		actService = actionFileService;
		String reqXml = null;
		InputStream inputStream  = null;
		StringBuffer sb = null;
		String response = null;
		Response ruleStatNotifyDTO = null;
		System.out.println("in servletttttttttt");
		try{
			inputStream = new BufferedInputStream(req.getInputStream(),32 * 1024);
			sb = new StringBuffer();
	    	int character = inputStream.read();
	    	while (character != -1) {
	    		
	    		sb.append((char) character);
	    		character = inputStream.read();
	    	}
	    	
	    	System.out.println(" The reqst recived is  "+sb.toString());
	    	
	    	if (sb.toString() != null && !sb.toString().equals(""))
	    		reqXml = sb.toString();
	    	if(reqXml == null || reqXml.trim().equals("")){
	    		response = "Request Xml is Empty";
	    		throw new Exception("Request Xml is Empty");
	    	}
	    	ruleStatNotifyDTO = validateReqXml(reqXml);
	    	System.out.println("ruleStatNotifyDTO>>>"+ruleStatNotifyDTO);
	    	 /*ArrayList<Param> params =(ArrayList<Param>) ruleStatNotifyDTO.getData().getDetailReq().getLists();
	   	
	    	List<ActionFileTO> actionFileTOs = new ArrayList<ActionFileTO>();
	    	for (Param param : params) {
	    		System.out.println("param Name>>>"+param.getName());
	    		if(param.getName() != null && !param.getName().trim().equals("ScheduleId") && !param.getName().trim().equals("NODE_NAME") 
	    				&& !param.getName().trim().equals("EXECUTION_LEVEL") &&
	    				!param.getName().trim().equals("EXECUTION_TIME")){
	    			System.out.println("Inside param Name>>>"+param.getName());
	    			ActionFileTO actionFileTO = new ActionFileTO();
	    				actionFileTO.setActionId(param.getName());
	    				actionFileTO.setActionFileName(param.getValue());
	    				actionFileTO.setCounts(param.getCount());
	    				actionFileTOs.add(actionFileTO);
	    		}
			}
	    	System.out.println("Size.>>"+actionFileTOs.size());
	    	for (ActionFileTO actionFileTO : actionFileTOs) {
				for (Param param : params) {
					if(param.getName().trim().equals("ScheduleId"))
						actionFileTO.setScheduledId(param.getValue());
					if(param.getName().trim().equals("NODE_NAME"))
						actionFileTO.setNodeName(param.getValue());
					if(param.getName().trim().equals("EXECUTION_LEVEL"))
						actionFileTO.setExecutionLevel(Integer.parseInt(param.getValue()));
					if(param.getName().trim().equals("EXECUTION_TIME")){
						DateFormat sdf = new SimpleDateFormat("ddMMyyyy HH:mm:ss");
						//Date date =sdf.parse(param.getName().trim());
						//actionFileTO.setExecutionTime(new Date());
					}
				}
				System.out.println(actionFileTO);
			}
	    	//actionFileService.saveFileDetails(actionFileTOs);
*/	    	
	    	
	    //	Cache.workerPool.addTask(ruleStatNotifyDTO);
			
		}catch (CommonException e) {
			response = e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			reqXml = null;
			req = null;
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private Response validateReqXml(String reqXml) throws CommonException {
		Response ruleStatusNotificationDTO=null;
		try{
			ruleStatusNotificationDTO = (Response) RuleStatusParser.getRuleXStream().fromXML(reqXml);
			if(ruleStatusNotificationDTO.getReq_tran_id()==null || ruleStatusNotificationDTO.getReq_tran_id().trim().equals(""))
				throw new CommonException("No Transactionid in request");
			if(ruleStatusNotificationDTO.getTimestamp()==null || ruleStatusNotificationDTO.getTimestamp().equals(""))
				throw new CommonException("No Timestamp in request");
			if(ruleStatusNotificationDTO.getData()==null)
				throw new CommonException("Parameters are missing");
			
		}catch (XStreamException e) {
			throw new CommonException("Invalid Xml "+reqXml);
		}
		return ruleStatusNotificationDTO;
	}
}
