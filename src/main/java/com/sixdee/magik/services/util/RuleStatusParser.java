/**
 * 
 */
package com.sixdee.magik.services.util;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


public class RuleStatusParser {

	private static XStream ruleReqXStreamParser = null;

	private RuleStatusParser() {

	}

	public static XStream getRuleXStream() {

		System.out.println("----------------> Came here for Parsing the xml ");

		if (ruleReqXStreamParser == null) {
			synchronized (XStream.class) {
				if (ruleReqXStreamParser == null) {
					ruleReqXStreamParser = new XStream(new XppDriver() {
						public HierarchicalStreamWriter createWriter(Writer out) {
							return new PrettyPrintWriter(out) {
								boolean cdata = true;

								public void startNode(String name, Class clazz) {
									super.startNode(name, clazz);
									// System.out.println("name - "+name+" clazz - "+clazz);
									// cdata = (name.equalsIgnoreCase("Dat") );
								}

								protected void writeText(QuickWriter writer,
										String text) {
									if (cdata) {
										// System.out.println("text- "+text);
										if (!text.equals("")) {
											writer.write("<![CDATA[");
											writer.write(text);
											writer.write("]]>");
										} else {
											writer.write(text);
										}
									} else {
										writer.write(text);
									}
								}
							};
						}
					});
					initRuleXStream();
				}
			}
		}
		return ruleReqXStreamParser;
	}

	private static void initRuleXStream() {
		
		
		  ruleReqXStreamParser.alias("REQ", Request.class);
		  ruleReqXStreamParser.aliasField("FEATURE", Request.class, "featureId");
		  ruleReqXStreamParser.aliasField("REQ-TRANS-ID", Request.class, "req_tran_id");
		  ruleReqXStreamParser.aliasField("RESP-TRANS-ID", Request.class, "resp_tran_id");
		  ruleReqXStreamParser.aliasField("USERNAME", Request.class, "username");
		  ruleReqXStreamParser.aliasField("PASSWORD", Request.class, "password");
		  ruleReqXStreamParser.aliasField("APP-NAME", Request.class, "app_name");
		  ruleReqXStreamParser.aliasField("MSG-ORIGIN", Request.class, "msg_origin");
		  ruleReqXStreamParser.aliasField("MSG-DEST", Request.class, "msg_dest");
		  ruleReqXStreamParser.aliasField("TIME-STAMP", Request.class, "timestamp");
		  ruleReqXStreamParser.aliasField("RESP-CODE", Request.class, "respCode");
		  ruleReqXStreamParser.aliasField("RESP-DESC", Request.class, "resp_desc");
		  ruleReqXStreamParser.aliasField("DATA", Request.class, "data");
		
		  ruleReqXStreamParser.alias("RESP", Response.class);
		  ruleReqXStreamParser.aliasField("FEATURE", Response.class, "featureId");
		  ruleReqXStreamParser.aliasField("REQ-TRANS-ID", Response.class, "req_tran_id");
		  ruleReqXStreamParser.aliasField("RESP-TRANS-ID", Response.class, "resp_tran_id");
		  ruleReqXStreamParser.aliasField("MSG-ORIGIN", Response.class, "msg_origin");
		  ruleReqXStreamParser.aliasField("MSG-DEST", Response.class, "msg_dest");
		  ruleReqXStreamParser.aliasField("TIME-STAMP", Response.class, "timestamp");
		  ruleReqXStreamParser.aliasField("RESP-CODE", Response.class, "respCode");
		  ruleReqXStreamParser.aliasField("RESP-DESC", Response.class, "resp_desc");
		  ruleReqXStreamParser.aliasField("DATA", Response.class, "data");
		  
		  ruleReqXStreamParser.alias("DATA", Data.class);
		  ruleReqXStreamParser.aliasField("DETAIL", Data.class, "detailReq");
		 
		  

		  ruleReqXStreamParser.alias("DETAIL", Detail.class);
		  ruleReqXStreamParser.addImplicitCollection(Detail.class, "lists");
		  ruleReqXStreamParser.aliasField("PARAM", Detail.class, "lists");
		  
		  ruleReqXStreamParser.alias("PARAM", Param.class);
		  ruleReqXStreamParser.addImplicitCollection(Param.class, "multiParams");
		  ruleReqXStreamParser.aliasField("NAME", Param.class, "name");
		  ruleReqXStreamParser.aliasField("VALUE", Param.class, "value");
		  ruleReqXStreamParser.aliasField("COUNT", Param.class, "count");
		  ruleReqXStreamParser.aliasField("SAMPLING-NAME", Param.class, "samplingName");
		  ruleReqXStreamParser.aliasField("NODE-NAME", Param.class, "nodeName");
		  ruleReqXStreamParser.aliasField("END-NODE", Param.class, "endNode");
		  ruleReqXStreamParser.aliasField("MULTI-PARAM", Param.class, "multiParam");
		  
		  ruleReqXStreamParser.alias("MULTI-PARAM", MultiParam.class);
		  ruleReqXStreamParser.addImplicitCollection(MultiParam.class, "params");
		  ruleReqXStreamParser.aliasField("PARAM", DetailResp.class, "param1");
		  
		
		
		// log.info("setQuickPushReqXStream process starting up");
		/*ruleReqXStreamParser.alias("Request", Response.class);
		ruleReqXStreamParser.aliasField("requestId",
				RuleStatusNotificationDTO.class, "requestId");
		ruleReqXStreamParser.aliasField("msisdn",
				RuleStatusNotificationDTO.class, "msisdn");
		ruleReqXStreamParser.aliasField("timeStamp",
				RuleStatusNotificationDTO.class, "timeStamp");
		ruleReqXStreamParser.aliasField("keyWord",
				RuleStatusNotificationDTO.class, "keyWord");
		ruleReqXStreamParser.aliasField("dataSet",
				RuleStatusNotificationDTO.class, "dataSet");
		ruleReqXStreamParser.addImplicitCollection(DataSet.class, "tagList");
		ruleReqXStreamParser.alias("param", Tag.class);
		ruleReqXStreamParser.aliasField("id", Tag.class, "id");
		ruleReqXStreamParser.aliasField("value", Tag.class, "value");*/

	}

}
