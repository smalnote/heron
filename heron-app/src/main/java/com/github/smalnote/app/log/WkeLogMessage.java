package com.github.smalnote.app.log;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.apache.logging.log4j.message.StringMapMessage;
import org.apache.logging.log4j.util.SortedArrayStringMap;

@JsonSubTypes({@JsonSubTypes.Type(value = SortedArrayStringMap.class, name = "data")})
public class WkeLogMessage extends StringMapMessage {

    public static final String INTVER = "intVer";
    public static final String WKECOD = "wkeCod";
    public static final String EACNBR = "eacNbr";
    public static final String CLTNBR = "cltNbr";
    public static final String TRXCOD = "trxCod";
    public static final String APPNBR = "appNbr";
    public static final String SAACOD = "saaCod";
    public static final String TAACOD = "taaCod";
    public static final String FNDCOD = "fndCod";
    public static final String TRXAMT = "trxAmt";
    public static final String TRXQTY = "trxQty";
    public static final String ERRCOD = "errCod";
    public static final String ERRCD2 = "errCd2";
    public static final String ERRMSG = "errMsg";

    public WkeLogMessage() {
        put(INTVER, "");
        put(WKECOD, "");
        put(EACNBR, "");
        put(CLTNBR, "");
        put(TRXCOD, "");
        put(APPNBR, "");
        put(SAACOD, "");
        put(TAACOD, "");
        put(FNDCOD, "");
        put(TRXAMT, "");
        put(TRXQTY, "");
        put(ERRCOD, "");
        put(ERRCD2, "");
        put(ERRMSG, "");
    }

    public void setWkeCod(String wkeCod) {
        put(WKECOD, wkeCod);
    }

    public void setErrCod(String errCod) {
        put(ERRCOD, errCod);
    }

    public void setErrMsg(String errMsg) {
        put(ERRMSG, errMsg);
    }

    public String getWkeCod() {
        return get(WKECOD);
    }

    public String getErrCod() {
        return get(ERRCOD);
    }

    public String getErrMsg() {
        return get(ERRMSG);
    }

    @Override
    public String toString() {
        return String.format("wkeCod=%s, errCod=%s, errMsg=%s", getWkeCod(), getErrCod(), getErrMsg());
    }

}
