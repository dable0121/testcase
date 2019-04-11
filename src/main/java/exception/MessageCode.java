package exception;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

/**
 * @author Dable
 * @date 2015年10月13日
 */
public class MessageCode {

    protected static final Logger logger = LoggerFactory.getLogger(MessageCode.class);
    protected static Map<String, String> errMsgs = null;
    private static String encoding = "utf-8";
    public static final String SUCCESS = MessageCodeEnum.SUCCESS.code;
    public static final String ERROR = MessageCodeEnum.ERROR.code;


    /**
     * 错误码枚举，约定MC=MessageCode
     * code码	错误信息
     * 系统级别      -00开头
     * 数据库异常    -99开头
     * 订单错误码    -10开头
     * 交易错误码    -11开头
     * 授权调用异常  -12开头
     * CRM调用异常   -13开头
     * 下单系统异常   -14开头
     * 商品查询异常   -15开头
     * 换商品异常     -16开头
     * 审批异常       -17开头
     * 资产异常       -18开头
     * 线下支付异常    -19开头
     * 注：根据需要可自行约定
     */
    public enum MessageCodeEnum {
        DATA_ACCESS_EXCEPTION("990001", "数据访问异常"),
        HTTP_INVOKE_ERROR("990002", "HTTP调用返回码{0}异常"),
        DB_INSERT_ERROR("990003", "数据库插入失败"),

        //通用异常
        URL_FORMAT_INVALID("000001", "URL格式非法"),
        DATA_NOT_FOUND("000002", "数据不存在"),
        NET_CALL_ERROR("000003", "接口调用失败"),
        DATA_NOT_UNIQUE("000004", "数据已存在"),
        ENCODING_ERROR("000005", "编码错误"),
        SIGNATURE_INVALID("000006", "签名非法"),
        NOT_SUPPORTED("000007", "未实现"),
        SIGNATURE_NOT_FOUND("000008", "签名不存在"),
        PARAMETER_NOT_FOUND("000009", "参数缺失"),
        PARAMETER_NOT_VALID("000010", "参数非法"),
        SEND_EMAIL_ERROR("000011", "发送邮件异常"),
        SEND_SMS_ERROR("000012", "发送短信异常"),
        AUTH_ERROR("000013", "授权异常"),
        AC_AUTH_ERROR("000014", "用户中心授权异常"),
        SERVICE_EXCEPTION("000015", "服务异常"),
        QUEUE_IS_FULL("000016", "处理队列已满，请稍后重试"),
        USER_ILLEGAL("000017", "用户非法"),

        //订单系统异常
        ORDER_APP_INFO_MISSING("100001", "应用信息缺失"),
        ORDER_LOCK_NUM_ASSETS_NOT_EXIST_IN_AUTH("100002", "锁资产查询不存在"),
        ORDER_STATUS_INVALID("100003", "订单状态不正确"),
        ORDER_LOCK_IS_NULL("100004", "订单{0}下锁号为空"),
        ORDER_CHILD_ORDER_IS_NULL("100005", "组合订单子订单为空"),
        INVOICE_NOT_VALID("100006", "发票填写信息未通过验证"),
        ORDER_NOT_ONLINE_PAY("100007", "非在线支付订单，不能支付"),
        ORDER_TASK_ERROR_CAN_NOT_NULL("100008", "非在线支付订单，不能支付"),
        GMSPID_PARAM_FORMAT_ERROR("100009", "助记符传入错误，尝试检查助记符是否全数字或者助记符之间是否英文逗号分隔"),
        ORDER_PRICE_ERROR("100010", "订单价格异常！"),
        ORDER_LOCK_ERROR("100011", "{0}锁有未支付订单，请取消或支付后再试。"),
        ORDER_DEALING_ERROR("100012", "订单正在处理中，请稍后再试。"),
        INVOICE_TAX_PAYER_CODE_TOO_LONG("100013", "订单参数：纳税人识别号为15到20个字符"),

        //授权异常
        AUTH_INVOKE_ACCESS_TOKEN("120000", "调用token过期"),
        AUTH_INVOKE_FAILED("120001", "调用auth失败"),
        AUTH_PARAM_ERROR("120002", "调用auth{0}参数{1}未定义"),
        AUTH_SYSTEM_ERROR("120003", "调用授权平台{0}异常"),
        AUTH_LOCK_FOR_15M("120004", "订单{0}授权正在处理，已加锁，请{1}分后重试！"),
        AUTH_LV_ERROR_PRODID_IS_NULL("120005", "授权标准按照-产品id，但是orderitemid{0}，product_id为空！"),
        AUTH_LV_ERROR_PRODUCT_IDENTITY_IS_NULL("120006", "授权标准按照-助记符，但是orderitemid{0}，product_identity为空！"),
        AUTH_INVOKE_DEV_ERROR("120007", "授权调用开发中心异常"),
        AUTH_INVOKE_RETRY_ERROR("120008", "授权调用重试结束,出现异常"),
        AUTH_INVOKE_FILL_UP_TIME_LT_NOW("120009", "授权调用,锁下助记符授权时间均小于当前时间，不授权这把锁{0}"),
        AUTH_INVOKE_LOCK_MODE_IS_NULL("120010", "不存在可用状态的该锁{0}资产"),//锁号{0}锁模式为空
        AUTH_INVOKE_ADD_PRODUCTS_IS_NULL("120011", "换购订单{0}添加产品，查询结果为空！"),
        AUTH_INVOKE_DEL_LICENSEIDS_IS_NULL("120012", "换购订单{0}删除授权列表，查询结果为空！"),
        AUTH_LOCK_QUERY_RETURN_NOT_INPUT("120013", "查询锁号{0}与返回锁号{1}不一致！"),
        AUTH_PRODUCTS_NULL("120014", "订单{0}从开发中心查询产品id下助记符列表为空，无法授权！请检查订单项所有产品id下是否含有助记符！"),

        //CRM异常
        CRM_INVOKE_FAILED("130000", "CRM调用失败"),

        //下单系统异常 - 14开头
        LOCK_NOT_EXIST("140000", "锁没查询到"),
        SUBMIT_ORDER_ERROR("140001", "下单异常"),
        ORDER_CENTER_ERROR("140002", "订单中心异常"),
        ORDER_CENTER_DATA_NOT_FOUND("140003", "订单中心还未下单"),
        ORDER_CENTER_PRICE_ERROR("140004", "订单价格错误，请核对商品价格"),

        //#商品查询异常 - 15开头
        COMMODITY_LOCK_NOT_EXSIT_GMSID("150000", "锁号{0}不包含助记符{1}"),

        //换商品异常 - 16开头
        COMMODITY_CHANGE_NO_RENEW("160000", "该锁{0}没有在新系统中做过续费，暂无法直接进行操作"),
        COMMODITY_CHANGE_NOT_EXSIT("160001", "该锁{0}状态不可用，请核查后再做处理"),
        COMMODITY_CHANGE_NOT_CACHED("160002", "该锁{0}分组资产缓存失效，请重新查询锁号资产!"),
        COMMODITY_CHANGE_NOT_SATISFY("160003", "该锁{0}内暂时无法通过当前功能进行补换购，请选择其他处理方式"),
        COMMODITY_CHANGE_PROD_NOT_EXSIT("160004", "该锁{0}在时间段{1}内，没有产品{2},不能做删除操作!"),
        COMMODITY_CHANGE_PROD_LICENSE_NOT_EXSIT("160005", "产品id{0}缓存的授权id为空!"),
		    COMMODITY_CHANGE_AUTH_LOCK("160006", "订单{0}授权正在处理，已加锁，请{1}分后重试！"),
        COMMODITY_CHANGE_TXTYPE_NOT_SUPPORT("160007", "该锁{0}查询分组，传入交易类型{1}不支持，目前支持4或者2交易类型"),
        COMMODITY_CHANGE_GT_TIMES("160008", "该锁{0}换购数超{1}次！"),
        COMMODITY_CHANGE_NOT_CHANGE("160009", "该锁{0}为不可补锁，不能{1}！"),
        CHANGE_HAS_NO_PRODS("160010", "该锁{0}没有可换或可补的产品！"),
        CHANGE_NOT_PERMIT("160011", "您输入的锁号{0}不在此次升级范围内！"),

        // 审批异常 - 17开关
        APPROVAL_MORE_THAN_TWICE("170000","该订单已被他人审批，请您刷新后查看。"),

        // 资产异常 - 18开关
        ASSET_NOT_AVAILABLE("180000", "锁号{0}有订单正在处理中（同步中或审批中），请确认后再试！"),
        // 线下支付异常    -19开头
        ORDER_ID_NOT_EXIST("190000", "订单号{0}不存在"),
        APP_MAIN_ACCOUNT_NULL_OR_PRIDER_ACCOUNT_NULL("190001", "银企直联账号或者注册企业账号不能为空"),
        SUB_ACCOUNT_ORDER_NULL("190002", "获取子账号时，订单id不能为空"),
        ORDER_NOT_COMPANY_TRANSFER("190003", "非线下转账订单，不能获取子账号"),
        RESPONSE_XML_PARSE_ERROR("190004", "-【交易平台-交易识别】获取前置机主账号交易信息失败，响应报文解析失败"),
        NOTIFY_ORDER_REALAMOUNT_ERROR("190005", "通知订单系统收款通知失败"),
        GET_TRANS_INFO_ERROR("190006", "-【交易平台-交易识别】获取前置机主账号交易信息失败，请尽快查看前置机服务状态是否正常！"),
        AMOUNT_NOT_GREATER_THAN_ZERO("190007", "转账金额不大于0，不需要调线下转账！"),
        AMOUNT_IN_AND_OUT_NOT_EQUAL("190008", "转出金额转入金额不等！"),
        AMOUNT_OUT_MUST_NEGATIVE("190009", "订单{0}转出金额必须为负数！"),

        //系统级别code//在这个上面添加，方便复制
        SUCCESS("000000", "成功"),
        ERROR("999999", "系统异常");

        private String code;
        private String message;

        MessageCodeEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static MessageCodeEnum getEnum(String code) {
            for (MessageCodeEnum enum_ : MessageCodeEnum.values()) {
                if (enum_.getCode().equals(code)) {
                    return enum_;
                }
            }
            return MessageCodeEnum.ERROR;
        }

        public static String getMessage(String code) {
            for (MessageCodeEnum enum_ : MessageCodeEnum.values()) {
                if (enum_.getCode().equals(code)) {
                    return enum_.getMessage();
                }
            }
            return MessageCodeEnum.ERROR.getMessage();
        }


        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }
    }


    static {
        errMsgs = initMessageMap();
    }

    /**
     * 初始化错误信息,从资源文件中读取错误信息映射关系
     *
     * @return Map<String,String> 信息集合
     */
    public static Map<String, String> initMessageMap() {
        Map<String, String> map = new HashMap<String, String>(64);
        MessageCode.MessageCodeEnum[] list = MessageCode.MessageCodeEnum.values();
        for (MessageCode.MessageCodeEnum mce :
                list) {
            map.put(mce.code, mce.message);
        }
        return map;
    }

    /**
     * 根据错误编码得到错误信息
     *
     * @param code 错误编码
     * @return 错误信息<String>
     */
    public static String getMsg(String code) {
        String str = errMsgs.get(code);
        if (str == null)
            return "";
        return str;
    }

    /**
     * 根据错误编码得到错误信息
     *
     * @param code 错误编码
     * @param args 动态参数 用于给{0}{1}{2}...等赋值
     * @return 错误信息<String>
     */
    public static String getMsg(String code, Object... args) {
        String str = errMsgs.get(code);
        if (str == null) {
            return "";
        }
        return MessageFormat.format(str, args);
    }

    /**
     * 根据异常类得到 相应的错误编码
     *
     * @param ex <Exception> 异常类
     * @return 错误编码<String>
     */
    public static String getErrorCodeByException(Exception ex) {
        logger.error("获取异常码，打印原始异常堆栈：", ex);
        String errorCode = MessageCodeEnum.ERROR.code;
        if (ex instanceof DataAccessException) {
            errorCode = MessageCodeEnum.DATA_ACCESS_EXCEPTION.code;
        }
        //Todo 需要处理的异常信息封装，根据需要添加进来
        return errorCode;
    }


}
