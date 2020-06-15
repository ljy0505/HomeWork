package com.wsy.everylesson.net;


//TODO 接口
public interface ApiService {
    String sBaseUrl = "https://cdwan.cn/api/";


/*@GET("topic/list")
Flowable<TopicBean> getTopic2(@Query("page") int page,
                              @Query("size") int size);*/
    /*
    *//**
     * 专题 列表
     * @param page 页码,从1开始
     * @param size 每页返回的数据数量
     * @return
     *//*
    @GET("topic/list")
    Observable<TopicBean> getTopic(@Query("page") int page,
                                   @Query("size") int size);
    //flowable也是rxjava的被观察者,使用起来和Observable一样的,但是它支持背压模式
    @GET("topic/list")
    Flowable<TopicBean> getTopic2(@Query("page") int page,
                                  @Query("size") int size);

    *//**
     * 分类竖着导航
     * @return
     *//*
    @GET("catalog/index")
    Flowable<SortTabBean> getSortTab();

    *//**
     * 分类右边对应的当前分类的数据
     * @return
     *//*
    @GET("catalog/current")
    Flowable<SortItemBean> getSortItem(@Query("id") int id);


    //http://cdwan.cn/goods/category?id=1005007
    *//**
     * 商品分类的顶部导航数据接口
     * @return
     *//*
    @GET("goods/category")
    Flowable<GoolsListTabBean> getGoolsTab();
    *//**
     * 商品详情列表数据
     * @return
     *//*
    @GET("goods/list")
    Flowable<GoolsListIRecyBean> getGoolsRecy(@Query("categoryId") int categoryId,
                                              @Query("page") int page,
                                              @Query("size") int size);


    *//**
     * 商品购买详情页
     * @param id  商品的id
     * @return
     *//*
    @GET("goods/detail")
    Flowable<GoodsDetail> getGoodsDetail(@Query("id") int id);

 *//*   *//**//**
     * 商品购买详情页底部商品列表数据
     * @return
     *//*
    @GET("goods/related")
    Flowable<RelateGoods> getRelateGoods(@Query("id") int id);
    *//**
     * 登录接口
     *//*
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> onLogin(@Field("nickname") String nickname,
                                @Field("password") String password);

    *//**
     * 注册接口[{"key":"Client-Type","value":"ANDROID","type":"text"}]
     *//*
    @POST("auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> onRegister(@Field("nickname") String nickname,
                                      @Field("password") String password);

    *//**
     * 添加到购物车
     *//*
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCartBean> addCart(@Header("X-Nideshop-Token") String token,
                                  @Field("goodsId") int goodsId,
                                  @Field("number") int number,
                                  @Field("productId") int productId);

    *//**
     * 获取购物车数据
     * @param token
     * @return
     *//*
    @GET("cart/index")
    Flowable<AddCartBean> getCartDate(@Header("X-Nideshop-Token") String token);

    *//**
     * 添加到购物车
     *//*
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCartBean> addCart2(@Field("goodsId") int goodsId,
                                   @Field("number") int number,
                                   @Field("productId") int productId);

    *//**
     * 获取购物车数据
     * @return
     *//*
    @GET("cart/index")
    Flowable<AddCartBean> getCartDate2();

    *//**
     * 商品总数
     * @return
     *//*
    @GET("goods/count")
    Flowable<CartSum> getCartSum();


    *//**
     * 商品更新
     *//*
    @POST("cart/update")
    @FormUrlEncoded
    Flowable<AddCartBean> updateNumber(@Field("productId") int productId,
                                       @Field("goodsId") int goodsId,
                                       @Field("number") int number,
                                       @Field("id") Long id);

    *//**
     * 删除购物车商品
     *//*
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<AddCartBean> onDeleteGoods(@Field("productIds") String productIds);


    *//**
     * 首页
     * @return
     *//*
    @GET("index")
    Flowable<HomeBean> getHomeDate();
    //https://cdwan.cn/api/goods/list?keyword=母亲&page=1&size=10&sort=default&order=desc&categoryId=0
    //keyword:关键字
    //page:页码,1开始
    //size:每页数量
    //sort:分类,每页传default
    //order:排序 desc降序 ,asce升序
    //categoryid:分类id,没有传0

    *//**
     * 搜索
     * @return
     *//*
    @GET("goods/list/1/10/default/asce/0")
    Flowable<SearchContentBean> getSearchDate(@Query("keyword") String keyword);
*//*     @GET("goods/list{keyword}/1/10/{sort}/{order}&categoryId=0")
   Flowable<SearchContentBean> getSearchDate(@Path("keyword") String keyword,
                                              @Query("page") int page,
                                              @Query("size") int size,
                                              @Path("sort") String sort,
                                              @Path("order") String order,
                                              @Query("categoryId") int categoryId);*/
}
