package po;

import java.sql.Timestamp;
import java.util.Objects;

public class OrderRecordPO {

  private String id;
  private String ngtradeOrderId;
  private Long userId;
  private Long tradeOrderId;
  private String invoiceId;
  private String parentOrderId;
  private String appKey;
  private Integer price;
  private Byte orderType;
  private Byte assetType;
  private Byte payType;
  private Timestamp payTime;
  private Byte payProvider;
  private String assetNum;
  private Byte composite;
  private Byte orderStatus;
  private Byte authStatus;
  private Byte crmOrderStatus;
  private Byte crmPaymentStatus;
  private String crmOrderNum;
  private String crmOperator;
  private String crmCustomerId;
  private String crmCustomerName;
  private String comment;
  private Timestamp createTime;
  private Timestamp updateTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getNgtradeOrderId() {
    return ngtradeOrderId;
  }

  public void setNgtradeOrderId(String ngtradeOrderId) {
    this.ngtradeOrderId = ngtradeOrderId;
  }


  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  public Long getTradeOrderId() {
    return tradeOrderId;
  }

  public void setTradeOrderId(Long tradeOrderId) {
    this.tradeOrderId = tradeOrderId;
  }


  public String getParentOrderId() {
    return parentOrderId;
  }

  public void setParentOrderId(String parentOrderId) {
    this.parentOrderId = parentOrderId;
  }


  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }


  public Byte getOrderType() {
    return orderType;
  }

  public void setOrderType(Byte orderType) {
    this.orderType = orderType;
  }


  public Byte getAssetType() {
    return assetType;
  }

  public void setAssetType(Byte assetType) {
    this.assetType = assetType;
  }



  public Byte getPayType() {
    return payType;
  }

  public void setPayType(Byte payType) {
    this.payType = payType;
  }


  public Timestamp getPayTime() {
    return payTime;
  }

  public void setPayTime(Timestamp payTime) {
    this.payTime = payTime;
  }


  public Byte getPayProvider() {
    return payProvider;
  }

  public void setPayProvider(Byte payProvider) {
    this.payProvider = payProvider;
  }


  public String getAssetNum() {
    return assetNum;
  }

  public void setAssetNum(String assetNum) {
    this.assetNum = assetNum;
  }


  public Byte getComposite() {
    return composite;
  }

  public void setComposite(Byte composite) {
    this.composite = composite;
  }


  public Byte getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Byte orderStatus) {
    this.orderStatus = orderStatus;
  }


  public Byte getAuthStatus() {
    return authStatus;
  }

  public void setAuthStatus(Byte authStatus) {
    this.authStatus = authStatus;
  }


  public Byte getCrmOrderStatus() {
    return crmOrderStatus;
  }

  public void setCrmOrderStatus(Byte crmOrderStatus) {
    this.crmOrderStatus = crmOrderStatus;
  }


  public Byte getCrmPaymentStatus() {
    return crmPaymentStatus;
  }

  public void setCrmPaymentStatus(Byte crmPaymentStatus) {
    this.crmPaymentStatus = crmPaymentStatus;
  }


  public String getCrmOrderNum() {
    return crmOrderNum;
  }

  public void setCrmOrderNum(String crmOrderNum) {
    this.crmOrderNum = crmOrderNum;
  }


  public String getCrmOperator() {
    return crmOperator;
  }

  public void setCrmOperator(String crmOperator) {
    this.crmOperator = crmOperator;
  }


  public String getCrmCustomerId() {
    return crmCustomerId;
  }

  public void setCrmCustomerId(String crmCustomerId) {
    this.crmCustomerId = crmCustomerId;
  }


  public String getCrmCustomerName() {
    return crmCustomerName;
  }

  public void setCrmCustomerName(String crmCustomerName) {
    this.crmCustomerName = crmCustomerName;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }


  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }


}
