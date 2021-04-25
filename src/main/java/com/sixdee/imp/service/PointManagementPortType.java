
package com.sixdee.imp.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import com.sixdee.imp.service.servicedto.req.xsd.AddPointsDTO;
import com.sixdee.imp.service.servicedto.req.xsd.PackageDTO;
import com.sixdee.imp.service.servicedto.req.xsd.PointDetailsDTO;
import com.sixdee.imp.service.servicedto.req.xsd.RedeemDTO;
import com.sixdee.imp.service.servicedto.req.xsd.RewardPointsDTO;
import com.sixdee.imp.service.servicedto.req.xsd.TransferPointsDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.PackageInfoDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.PointInfoDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.ResponseCodeDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.ResponseDTO;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PointManagementPortType", targetNamespace = "http://service.imp.sixdee.com")
@XmlSeeAlso({
    com.sixdee.imp.service.ObjectFactory.class,
    com.sixdee.imp.service.servicedto.common.xsd.ObjectFactory.class,
    com.sixdee.imp.service.servicedto.req.xsd.ObjectFactory.class,
    com.sixdee.imp.service.servicedto.resp.xsd.ObjectFactory.class
})
public interface PointManagementPortType {


    /**
     * 
     * @param addPointsDTO
     * @return
     *     returns com.sixdee.imp.service.servicedto.resp.xsd.ResponseCodeDTO
     */
    @WebMethod(action = "urn:addPoints")
    @WebResult(targetNamespace = "http://service.imp.sixdee.com")
    @RequestWrapper(localName = "addPoints", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.AddPoints")
    @ResponseWrapper(localName = "addPointsResponse", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.AddPointsResponse")
    public ResponseCodeDTO addPoints(
        @WebParam(name = "addPointsDTO", targetNamespace = "http://service.imp.sixdee.com")
        AddPointsDTO addPointsDTO);

    /**
     * 
     * @param transferPointsDTO
     * @return
     *     returns com.sixdee.imp.service.servicedto.resp.xsd.ResponseDTO
     */
    @WebMethod(action = "urn:transferPoints")
    @WebResult(targetNamespace = "http://service.imp.sixdee.com")
    @RequestWrapper(localName = "transferPoints", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.TransferPoints")
    @ResponseWrapper(localName = "transferPointsResponse", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.TransferPointsResponse")
    public ResponseDTO transferPoints(
        @WebParam(name = "transferPointsDTO", targetNamespace = "http://service.imp.sixdee.com")
        TransferPointsDTO transferPointsDTO);

    /**
     * 
     * @param transferLineDTO
     * @return
     *     returns com.sixdee.imp.service.servicedto.resp.xsd.ResponseDTO
     */
    @WebMethod(action = "urn:transferLine")
    @WebResult(targetNamespace = "http://service.imp.sixdee.com")
    @RequestWrapper(localName = "transferLine", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.TransferLine")
    @ResponseWrapper(localName = "transferLineResponse", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.TransferLineResponse")
    public ResponseDTO transferLine(
        @WebParam(name = "transferLineDTO", targetNamespace = "http://service.imp.sixdee.com")
        TransferPointsDTO transferLineDTO);

    /**
     * 
     * @param rewardPointsDTO
     * @return
     *     returns com.sixdee.imp.service.servicedto.resp.xsd.ResponseDTO
     */
    @WebMethod(action = "urn:rewardPointsCalculation")
    @WebResult(targetNamespace = "http://service.imp.sixdee.com")
    @RequestWrapper(localName = "rewardPointsCalculation", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.RewardPointsCalculation")
    @ResponseWrapper(localName = "rewardPointsCalculationResponse", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.RewardPointsCalculationResponse")
    public ResponseDTO rewardPointsCalculation(
        @WebParam(name = "rewardPointsDTO", targetNamespace = "http://service.imp.sixdee.com")
        RewardPointsDTO rewardPointsDTO);

    /**
     * 
     * @param packageDTO
     * @return
     *     returns com.sixdee.imp.service.servicedto.resp.xsd.PackageInfoDTO
     */
    @WebMethod(action = "urn:getPackages")
    @WebResult(targetNamespace = "http://service.imp.sixdee.com")
    @RequestWrapper(localName = "getPackages", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.GetPackages")
    @ResponseWrapper(localName = "getPackagesResponse", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.GetPackagesResponse")
    public PackageInfoDTO getPackages(
        @WebParam(name = "packageDTO", targetNamespace = "http://service.imp.sixdee.com")
        PackageDTO packageDTO);

    /**
     * 
     * @param redeemDTO
     * @return
     *     returns com.sixdee.imp.service.servicedto.resp.xsd.ResponseDTO
     */
    @WebMethod(action = "urn:redeemPoints")
    @WebResult(targetNamespace = "http://service.imp.sixdee.com")
    @RequestWrapper(localName = "redeemPoints", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.RedeemPoints")
    @ResponseWrapper(localName = "redeemPointsResponse", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.RedeemPointsResponse")
    public ResponseDTO redeemPoints(
        @WebParam(name = "redeemDTO", targetNamespace = "http://service.imp.sixdee.com")
        RedeemDTO redeemDTO);

    /**
     * 
     * @param pointDetailsDTO
     * @return
     *     returns com.sixdee.imp.service.servicedto.resp.xsd.PointInfoDTO
     */
    @WebMethod(action = "urn:getPointDetails")
    @WebResult(targetNamespace = "http://service.imp.sixdee.com")
    @RequestWrapper(localName = "getPointDetails", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.GetPointDetails")
    @ResponseWrapper(localName = "getPointDetailsResponse", targetNamespace = "http://service.imp.sixdee.com", className = "com.sixdee.imp.service.GetPointDetailsResponse")
    public PointInfoDTO getPointDetails(
        @WebParam(name = "pointDetailsDTO", targetNamespace = "http://service.imp.sixdee.com")
        PointDetailsDTO pointDetailsDTO);

}
