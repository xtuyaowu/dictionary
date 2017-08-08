package cc.chenzhihao.dictionary.config;

/**
 * Des : <dictionary> :
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/5 17:43
 */
public class CssSelectorConfig {

    //基础url
    private String baseUrl;

    //启始页号
    private int startPage;

    //结束页号
    private int endPage;

    //列表缓存队列的key
    private String redisListQueueKey;

    //缓存中公司数据列表的key
    private String redisCompanyListKey;
    private String nameCss = "";
    private String subNameCss = "";
    private String logoUrlCss = "";
    private String companyUrlCss = "";
    private String companyWeiboCss = "";
    private String companyWeixinCss = "";
    private String locationCss = "";
    private String establishTimeCss = "";
    private String scaleCss = "";
    private String industryCss = "";
    private String natureCss = "";
    private String developmentStageCss = "";
    private String stockCodeCss = "";
    private String descriptionCss = "";
    private String workLocateCss = "";
    private String nearSubwayCss = "";
    private String hasShuttleBusCss = "";
    private String tagsCss = "";
    private String financingSituationCss = "";
    private String operationSituationCss = "";
    private String superCompanyBackgroundCss = "";
    private String companyBackgroundCss = "";
    private String companyBackgroundImgUrlCss = "";

    @Override
    public String toString() {
        return "CssSelectorConfig{" +
                "baseUrl='" + baseUrl + '\'' +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", redisListQueueKey='" + redisListQueueKey + '\'' +
                ", redisCompanyListKey='" + redisCompanyListKey + '\'' +
                ", nameCss='" + nameCss + '\'' +
                ", subNameCss='" + subNameCss + '\'' +
                ", logoUrlCss='" + logoUrlCss + '\'' +
                ", companyUrlCss='" + companyUrlCss + '\'' +
                ", companyWeiboCss='" + companyWeiboCss + '\'' +
                ", companyWeixinCss='" + companyWeixinCss + '\'' +
                ", locationCss='" + locationCss + '\'' +
                ", establishTimeCss='" + establishTimeCss + '\'' +
                ", scaleCss='" + scaleCss + '\'' +
                ", industryCss='" + industryCss + '\'' +
                ", natureCss='" + natureCss + '\'' +
                ", developmentStageCss='" + developmentStageCss + '\'' +
                ", stockCodeCss='" + stockCodeCss + '\'' +
                ", descriptionCss='" + descriptionCss + '\'' +
                ", workLocateCss='" + workLocateCss + '\'' +
                ", nearSubwayCss='" + nearSubwayCss + '\'' +
                ", hasShuttleBusCss='" + hasShuttleBusCss + '\'' +
                ", tagsCss='" + tagsCss + '\'' +
                ", financingSituationCss='" + financingSituationCss + '\'' +
                ", operationSituationCss='" + operationSituationCss + '\'' +
                ", superCompanyBackgroundCss='" + superCompanyBackgroundCss + '\'' +
                ", companyBackgroundCss='" + companyBackgroundCss + '\'' +
                ", companyBackgroundImgUrlCss='" + companyBackgroundImgUrlCss + '\'' +
                '}';
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getRedisListQueueKey() {
        return redisListQueueKey;
    }

    public void setRedisListQueueKey(String redisListQueueKey) {
        this.redisListQueueKey = redisListQueueKey;
    }

    public String getRedisCompanyListKey() {
        return redisCompanyListKey;
    }

    public void setRedisCompanyListKey(String redisCompanyListKey) {
        this.redisCompanyListKey = redisCompanyListKey;
    }

    public String getNameCss() {
        return nameCss;
    }

    public void setNameCss(String nameCss) {
        this.nameCss = nameCss;
    }

    public String getSubNameCss() {
        return subNameCss;
    }

    public void setSubNameCss(String subNameCss) {
        this.subNameCss = subNameCss;
    }

    public String getLogoUrlCss() {
        return logoUrlCss;
    }

    public void setLogoUrlCss(String logoUrlCss) {
        this.logoUrlCss = logoUrlCss;
    }

    public String getCompanyUrlCss() {
        return companyUrlCss;
    }

    public void setCompanyUrlCss(String companyUrlCss) {
        this.companyUrlCss = companyUrlCss;
    }

    public String getCompanyWeiboCss() {
        return companyWeiboCss;
    }

    public void setCompanyWeiboCss(String companyWeiboCss) {
        this.companyWeiboCss = companyWeiboCss;
    }

    public String getCompanyWeixinCss() {
        return companyWeixinCss;
    }

    public void setCompanyWeixinCss(String companyWeixinCss) {
        this.companyWeixinCss = companyWeixinCss;
    }

    public String getLocationCss() {
        return locationCss;
    }

    public void setLocationCss(String locationCss) {
        this.locationCss = locationCss;
    }

    public String getEstablishTimeCss() {
        return establishTimeCss;
    }

    public void setEstablishTimeCss(String establishTimeCss) {
        this.establishTimeCss = establishTimeCss;
    }

    public String getScaleCss() {
        return scaleCss;
    }

    public void setScaleCss(String scaleCss) {
        this.scaleCss = scaleCss;
    }

    public String getIndustryCss() {
        return industryCss;
    }

    public void setIndustryCss(String industryCss) {
        this.industryCss = industryCss;
    }

    public String getNatureCss() {
        return natureCss;
    }

    public void setNatureCss(String natureCss) {
        this.natureCss = natureCss;
    }

    public String getDevelopmentStageCss() {
        return developmentStageCss;
    }

    public void setDevelopmentStageCss(String developmentStageCss) {
        this.developmentStageCss = developmentStageCss;
    }

    public String getStockCodeCss() {
        return stockCodeCss;
    }

    public void setStockCodeCss(String stockCodeCss) {
        this.stockCodeCss = stockCodeCss;
    }

    public String getDescriptionCss() {
        return descriptionCss;
    }

    public void setDescriptionCss(String descriptionCss) {
        this.descriptionCss = descriptionCss;
    }

    public String getWorkLocateCss() {
        return workLocateCss;
    }

    public void setWorkLocateCss(String workLocateCss) {
        this.workLocateCss = workLocateCss;
    }

    public String getNearSubwayCss() {
        return nearSubwayCss;
    }

    public void setNearSubwayCss(String nearSubwayCss) {
        this.nearSubwayCss = nearSubwayCss;
    }

    public String getHasShuttleBusCss() {
        return hasShuttleBusCss;
    }

    public void setHasShuttleBusCss(String hasShuttleBusCss) {
        this.hasShuttleBusCss = hasShuttleBusCss;
    }

    public String getTagsCss() {
        return tagsCss;
    }

    public void setTagsCss(String tagsCss) {
        this.tagsCss = tagsCss;
    }

    public String getFinancingSituationCss() {
        return financingSituationCss;
    }

    public void setFinancingSituationCss(String financingSituationCss) {
        this.financingSituationCss = financingSituationCss;
    }

    public String getOperationSituationCss() {
        return operationSituationCss;
    }

    public void setOperationSituationCss(String operationSituationCss) {
        this.operationSituationCss = operationSituationCss;
    }

    public String getSuperCompanyBackgroundCss() {
        return superCompanyBackgroundCss;
    }

    public void setSuperCompanyBackgroundCss(String superCompanyBackgroundCss) {
        this.superCompanyBackgroundCss = superCompanyBackgroundCss;
    }

    public String getCompanyBackgroundCss() {
        return companyBackgroundCss;
    }

    public void setCompanyBackgroundCss(String companyBackgroundCss) {
        this.companyBackgroundCss = companyBackgroundCss;
    }

    public String getCompanyBackgroundImgUrlCss() {
        return companyBackgroundImgUrlCss;
    }

    public void setCompanyBackgroundImgUrlCss(String companyBackgroundImgUrlCss) {
        this.companyBackgroundImgUrlCss = companyBackgroundImgUrlCss;
    }
}
