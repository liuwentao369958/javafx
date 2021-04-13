package bing.model;

//供货订单实体类
public class Dingdandao {
    private int id;
    private String orderno;
    private  int ordertype;
    private  int userid;
    private String title;
    private String category;
    private String  thumb;
    private double amount;
    private double price;
    private double total;
    private double replyamount;
    private double replyprice;
    private double replytotal;
    private int status;
    private int createtime;
    private String supplytime;
    private String address;
    private String remark;
    private  String packger;
    private String specification;
    private int purchid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getReplyamount() {
        return replyamount;
    }

    public void setReplyamount(double replyamount) {
        this.replyamount = replyamount;
    }

    public double getReplyprice() {
        return replyprice;
    }

    public void setReplyprice(double replyprice) {
        this.replyprice = replyprice;
    }

    public double getReplytotal() {
        return replytotal;
    }

    public void setReplytotal(double replytotal) {
        this.replytotal = replytotal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public String getSupplytime() {
        return supplytime;
    }

    public void setSupplytime(String supplytime) {
        this.supplytime = supplytime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPackger() {
        return packger;
    }

    public void setPackger(String packger) {
        this.packger = packger;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getPurchid() {
        return purchid;
    }

    public void setPurchid(int purchid) {
        this.purchid = purchid;
    }
}
