package cc.chenzhihao.dictionary.domain;

import cc.chenzhihao.dictionary.domain.base.BaseModel;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Des : <dictionary> : 公司
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/4 17:04
 */
@Entity
@Table(name = "company")
@DynamicInsert
@ApiModel
public class Company extends BaseModel implements Serializable {

    /**
     * 信息来源
     */
    @Column(name = "information_from")
    private String informationFrom;

    /**
     * 公司名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 简称
     */
    @Column(name = "sub_name")
    private String subName;

    /**
     * logo图片地址
     */
    @Column(name = "logo_url")
    private String logoUrl;

    /**
     * 公司网站地址
     */
    @Column(name = "company_url")
    private String companyUrl;

    /**
     * 公司微博
     */
    @Column(name = "company_weibo")
    private String companyWeibo;

    /**
     * 公司微信
     */
    @Column(name = "company_weixin")
    private String companyWeixin;

    /**
     * 公司总部地址
     */
    @Column(name = "location")
    private String location;

    /**
     * 公司创建时间
     */
    @Column(name = "establish_time")
    private String establishTime;

    /**
     * 公司规模
     */
    @Column(name = "scale")
    private String scale;

    /**
     * 所属行业
     */
    @Column(name = "industry")
    private String industry;

    /**
     * 企业性质
     */
    @Column(name = "nature")
    private String nature;

    /**
     * 发展阶段
     */
    @Column(name = "development_stage")
    private String developmentStage;

    /**
     * 股票代码
     */
    @Column(name = "stock_code")
    private String stockCode;

    /**
     * 一句话描述
     */
    @Lob
    @Column(name = "description")
    private String description;

    /**
     * 工作地址
     */
    @Column(name = "work_locate")
    private String workLocate;

    /**
     * 是否有地铁
     */
    @Column(name = "near_subway")
    private boolean nearSubway;

    /**
     * 是否有班车
     */
    @Column(name = "has_shuttle_bus")
    private boolean hasShuttleBus;

    /**
     * 企业标签
     */
    @Column(name = "tags")
    private String tags;

    /**
     * 融资情况
     */
    @Column(name = "financing_situation")
    private String financingSituation;

    /**
     * 经营情况
     */
    @Column(name = "operation_situation")
    private String operationSituation;

    /**
     * 母公司背景
     */
    @Column(name = "super_company_background")
    private String superCompanyBackground;

    /**
     * 公司背景
     */
    @Column(name = "company_background")
    private String companyBackground;

    /**
     * 公司背景图片
     */
    @Column(name = "company_background_img_url")
    private String companyBackgroundImgUrl;

    public String getInformationFrom() {
        return informationFrom;
    }

    public void setInformationFrom(String informationFrom) {
        this.informationFrom = informationFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompanyWeibo() {
        return companyWeibo;
    }

    public void setCompanyWeibo(String companyWeibo) {
        this.companyWeibo = companyWeibo;
    }

    public String getCompanyWeixin() {
        return companyWeixin;
    }

    public void setCompanyWeixin(String companyWeixin) {
        this.companyWeixin = companyWeixin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDevelopmentStage() {
        return developmentStage;
    }

    public void setDevelopmentStage(String developmentStage) {
        this.developmentStage = developmentStage;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkLocate() {
        return workLocate;
    }

    public void setWorkLocate(String workLocate) {
        this.workLocate = workLocate;
    }

    public boolean isNearSubway() {
        return nearSubway;
    }

    public void setNearSubway(boolean nearSubway) {
        this.nearSubway = nearSubway;
    }

    public boolean isHasShuttleBus() {
        return hasShuttleBus;
    }

    public void setHasShuttleBus(boolean hasShuttleBus) {
        this.hasShuttleBus = hasShuttleBus;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFinancingSituation() {
        return financingSituation;
    }

    public void setFinancingSituation(String financingSituation) {
        this.financingSituation = financingSituation;
    }

    public String getOperationSituation() {
        return operationSituation;
    }

    public void setOperationSituation(String operationSituation) {
        this.operationSituation = operationSituation;
    }

    public String getSuperCompanyBackground() {
        return superCompanyBackground;
    }

    public void setSuperCompanyBackground(String superCompanyBackground) {
        this.superCompanyBackground = superCompanyBackground;
    }

    public String getCompanyBackground() {
        return companyBackground;
    }

    public void setCompanyBackground(String companyBackground) {
        this.companyBackground = companyBackground;
    }

    public String getCompanyBackgroundImgUrl() {
        return companyBackgroundImgUrl;
    }

    public void setCompanyBackgroundImgUrl(String companyBackgroundImgUrl) {
        this.companyBackgroundImgUrl = companyBackgroundImgUrl;
    }

    @Override
    public String toString() {
        return "Company{" +
                "informationFrom='" + informationFrom + '\'' +
                ", name='" + name + '\'' +
                ", subName='" + subName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                ", companyWeibo='" + companyWeibo + '\'' +
                ", companyWeixin='" + companyWeixin + '\'' +
                ", location='" + location + '\'' +
                ", establishTime='" + establishTime + '\'' +
                ", scale='" + scale + '\'' +
                ", industry='" + industry + '\'' +
                ", nature='" + nature + '\'' +
                ", developmentStage='" + developmentStage + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", description='" + description + '\'' +
                ", workLocate='" + workLocate + '\'' +
                ", nearSubway=" + nearSubway +
                ", hasShuttleBus=" + hasShuttleBus +
                ", tags='" + tags + '\'' +
                ", financingSituation='" + financingSituation + '\'' +
                ", operationSituation='" + operationSituation + '\'' +
                ", superCompanyBackground='" + superCompanyBackground + '\'' +
                ", companyBackground='" + companyBackground + '\'' +
                ", companyBackgroundImgUrl='" + companyBackgroundImgUrl + '\'' +
                "} " + super.toString();
    }
}
