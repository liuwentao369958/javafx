package bing.model;

public class Dingdanjilu {
    private int uid;
    private String orderno;
    private int operatid;
    private int createtime;
    private int starttime;
    private int markettime;
    private int purchid;
    private int catid;
    private int realamount;
    private int realprice;
    private String packageno;
    private int type;
    private int status;
    private String info;

    public void setId(int uid) {
        this.uid = uid;
    }
    public int getId() {
        return uid;
    }
    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public int getOperatid() {
        return operatid;
    }

    public void setOperatid(int operatid) {
        this.operatid = operatid;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getMarkettime() {
        return markettime;
    }

    public void setMarkettime(int markettime) {
        this.markettime = markettime;
    }

    public int getPurchid() {
        return purchid;
    }

    public void setPurchid(int purchid) {
        this.purchid = purchid;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public int getRealamount() {
        return realamount;
    }

    public void setRealamount(int realamount) {
        this.realamount = realamount;
    }

    public int getRealprice() {
        return realprice;
    }

    public void setRealprice(int realprice) {
        this.realprice = realprice;
    }

    public String getPackageno() {
        return packageno;
    }

    public void setPackageno(String packageno) {
        this.packageno = packageno;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
