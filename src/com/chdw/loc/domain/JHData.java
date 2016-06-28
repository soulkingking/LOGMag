package com.chdw.loc.domain;

import java.util.List;

/**
 * Created by CaoBin on 2016/4/21.
 */
public class JHData {

    /**
     * resultcode : 200
     * reason : 查询成功
     * result : [{"name":"昌茂","navigation":"上海餐厅>>崇明县>>本帮江浙菜>>本帮菜>>昌茂","city":"上海","area":"崇明县","address":"崇明县长江农场(长江公路崇明黄河路)","phone":"","latitude":"31.67662","longitude":"121.53756","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"农家菜","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"昌茂酒家","navigation":"上海餐厅>>崇明县>>本帮江浙菜>>农家菜>>昌茂酒家","city":"上海","area":"崇明县","address":"崇明县北沿公路1647弄732号(近长江公路)","phone":"021-59668272","latitude":"31.67416","longitude":"121.5385","stars":"3.0","avg_price":"61","photos":"http://i3.dpfile.com/pc/f07e5f553e60d11dccd1a75e461d42c1/36504707_m.jpg","tags":"农家菜","all_remarks":"16","very_good_remarks":"1","good_remarks":"2","common_remarks":"1","bad_remarks":"0","very_bad_remarks":"0","product_rating":"6.3","environment_rating":"6.1","service_rating":"6.6","recommended_products":"","recommended_dishes":"红烧山羊肉,大闸蟹,呛虾,香辣螺蛳,白菜鹅肉,年糕白蟹,红烧猪脚,老母鸡汤,干烧胖头鱼,红烧野生黄鳝,白斩鸡","nearby_shops":""},{"name":"阿荣食府","navigation":"上海餐厅>>崇明县>>本帮江浙菜>>农家菜>>阿荣食府","city":"上海","area":"崇明县","address":"长江农场长江公路(北沿公路)","phone":"021-59667398","latitude":"31.67746","longitude":"121.53864","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"农家菜","all_remarks":"2","very_good_remarks":"2","good_remarks":"0","common_remarks":"0","bad_remarks":"0","very_bad_remarks":"0","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"红烧崇明羊肉,扁豆芋艿,毛蟹年糕,水煮鱼片,清炖土鸡,白斩鸡,红烧羊肉,醉虾,野生甲鱼汤,金瓜丝","nearby_shops":""},{"name":"宝岛度假村御花园中餐厅","navigation":"上海餐厅>>崇明县>>本帮江浙菜>>本帮菜>>宝岛度假村御花园中餐厅","city":"上海","area":"崇明县","address":"崇明县北沿公路(近东平国家森林公园南侧)","phone":"021-59339898","latitude":"31.67868","longitude":"121.539","stars":"0.0","avg_price":"0","photos":"http://i1.dpfile.com/2009-09-14/2707350_m.jpg","tags":"本帮菜","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"瀛都凯尚","navigation":"上海餐厅>>崇明县>>本帮江浙菜>>瀛都凯尚","city":"上海","area":"崇明县","address":"崇明县黄河路(近瀛都宾馆)","phone":"","latitude":"31.67561","longitude":"121.53932","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"本帮江浙菜","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""}]
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private int error_code;
    /**
     * name : 昌茂
     * navigation : 上海餐厅>>崇明县>>本帮江浙菜>>本帮菜>>昌茂
     * city : 上海
     * area : 崇明县
     * address : 崇明县长江农场(长江公路崇明黄河路)
     * phone :
     * latitude : 31.67662
     * longitude : 121.53756
     * stars : 0.0
     * avg_price : 0
     * photos : http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png
     * tags : 农家菜
     * all_remarks :
     * very_good_remarks :
     * good_remarks :
     * common_remarks :
     * bad_remarks :
     * very_bad_remarks :
     * product_rating :
     * environment_rating :
     * service_rating :
     * recommended_products :
     * recommended_dishes :
     * nearby_shops :
     */

    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String name;
        private String navigation;
        private String city;
        private String area;
        private String address;
        private String phone;
        private String latitude;
        private String longitude;
        private String stars;
        private String avg_price;
        private String photos;
        private String tags;
        private String all_remarks;
        private String very_good_remarks;
        private String good_remarks;
        private String common_remarks;
        private String bad_remarks;
        private String very_bad_remarks;
        private String product_rating;
        private String environment_rating;
        private String service_rating;
        private String recommended_products;
        private String recommended_dishes;
        private String nearby_shops;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNavigation() {
            return navigation;
        }

        public void setNavigation(String navigation) {
            this.navigation = navigation;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getAvg_price() {
            return avg_price;
        }

        public void setAvg_price(String avg_price) {
            this.avg_price = avg_price;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getAll_remarks() {
            return all_remarks;
        }

        public void setAll_remarks(String all_remarks) {
            this.all_remarks = all_remarks;
        }

        public String getVery_good_remarks() {
            return very_good_remarks;
        }

        public void setVery_good_remarks(String very_good_remarks) {
            this.very_good_remarks = very_good_remarks;
        }

        public String getGood_remarks() {
            return good_remarks;
        }

        public void setGood_remarks(String good_remarks) {
            this.good_remarks = good_remarks;
        }

        public String getCommon_remarks() {
            return common_remarks;
        }

        public void setCommon_remarks(String common_remarks) {
            this.common_remarks = common_remarks;
        }

        public String getBad_remarks() {
            return bad_remarks;
        }

        public void setBad_remarks(String bad_remarks) {
            this.bad_remarks = bad_remarks;
        }

        public String getVery_bad_remarks() {
            return very_bad_remarks;
        }

        public void setVery_bad_remarks(String very_bad_remarks) {
            this.very_bad_remarks = very_bad_remarks;
        }

        public String getProduct_rating() {
            return product_rating;
        }

        public void setProduct_rating(String product_rating) {
            this.product_rating = product_rating;
        }

        public String getEnvironment_rating() {
            return environment_rating;
        }

        public void setEnvironment_rating(String environment_rating) {
            this.environment_rating = environment_rating;
        }

        public String getService_rating() {
            return service_rating;
        }

        public void setService_rating(String service_rating) {
            this.service_rating = service_rating;
        }

        public String getRecommended_products() {
            return recommended_products;
        }

        public void setRecommended_products(String recommended_products) {
            this.recommended_products = recommended_products;
        }

        public String getRecommended_dishes() {
            return recommended_dishes;
        }

        public void setRecommended_dishes(String recommended_dishes) {
            this.recommended_dishes = recommended_dishes;
        }

        public String getNearby_shops() {
            return nearby_shops;
        }

        public void setNearby_shops(String nearby_shops) {
            this.nearby_shops = nearby_shops;
        }
    }
}
