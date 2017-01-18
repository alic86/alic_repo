package com.alic.third_part;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.ECLP.EclpOpenService.GoodsInfo;
import com.jd.open.api.sdk.domain.ECLP.EclpOpenService.PoItemModel;
import com.jd.open.api.sdk.domain.ECLP.EclpOpenService.QueryPoModel;
import com.jd.open.api.sdk.domain.ECLP.EclpOpenService.WarehouseOut;
import com.jd.open.api.sdk.request.ECLP.EclpGoodsQueryGoodsInfoRequest;
import com.jd.open.api.sdk.request.ECLP.EclpGoodsTransportGoodsInfoRequest;
import com.jd.open.api.sdk.request.ECLP.EclpGoodsUpdateGoodsInfoRequest;
import com.jd.open.api.sdk.request.ECLP.EclpMasterQueryWarehouseRequest;
import com.jd.open.api.sdk.request.ECLP.EclpPoAddPoOrderRequest;
import com.jd.open.api.sdk.request.ECLP.EclpPoCancalPoOrderRequest;
import com.jd.open.api.sdk.request.ECLP.EclpPoQueryPoOrderRequest;
import com.jd.open.api.sdk.response.ECLP.EclpGoodsQueryGoodsInfoResponse;
import com.jd.open.api.sdk.response.ECLP.EclpGoodsTransportGoodsInfoResponse;
import com.jd.open.api.sdk.response.ECLP.EclpGoodsUpdateGoodsInfoResponse;
import com.jd.open.api.sdk.response.ECLP.EclpMasterQueryWarehouseResponse;
import com.jd.open.api.sdk.response.ECLP.EclpPoAddPoOrderResponse;
import com.jd.open.api.sdk.response.ECLP.EclpPoCancalPoOrderResponse;
import com.jd.open.api.sdk.response.ECLP.EclpPoQueryPoOrderResponse;

public class JdTest {
	public static final String SERVER_URL = "https://api.jd.com/routerjson";
	public static final String ACCESS_TOKEN = "f03c99ec-1f2b-400c-aa08-a463dcb0396f";
	public static final String APP_KEY = "566042CD361D961F4F5BF215904A32EE";
	public static final String APP_SECRET = "2f2f20db55ef4170b5741b80969ea022";
	public static final String DEPT_NO = "EBU0020000000313";
	
	public static final String ISV_GOODS_NO = "INTELLIGENCE-SMALLTICKET";
	public static final String BAR_CODE = "possn";
	public static final String CATEGORY_NO = "7373";
	public static final String GOODS_NAME = "智能POS-扫码水牌";
	public static final Integer SAFE_DAYS = 0;
	
//	添加主商品信息
	@Test
	public void addGoods() throws JdException{
		EclpGoodsTransportGoodsInfoRequest request = new EclpGoodsTransportGoodsInfoRequest();
		request.setDeptNo(DEPT_NO);
		request.setIsvGoodsNo(ISV_GOODS_NO);
		request.setBarcodes(BAR_CODE);
		request.setThirdCategoryNo(CATEGORY_NO);
		request.setGoodsName(GOODS_NAME);
		request.setSafeDays(SAFE_DAYS);
		request.setAbbreviation(GOODS_NAME);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt = sdf.format(new Date());
		request.setTimestamp(dt);
		DefaultJdClient client = new DefaultJdClient(SERVER_URL,ACCESS_TOKEN,APP_KEY,APP_SECRET);
		EclpGoodsTransportGoodsInfoResponse response = client.execute(request);
		String goodsNo = response.getGoodsNo();
		System.out.println(goodsNo);
	}
	
//	查询主商品信息
	@Test
	public void findGoods() throws JdException{
		EclpGoodsQueryGoodsInfoRequest request=new EclpGoodsQueryGoodsInfoRequest();
		request.setDeptNo(DEPT_NO);
		request.setPageNo(1);
		request.setPageSize(10);
		DefaultJdClient client = new DefaultJdClient(SERVER_URL,ACCESS_TOKEN,APP_KEY,APP_SECRET);
		EclpGoodsQueryGoodsInfoResponse response=client.execute(request);
		List<GoodsInfo> goodsInfoList = response.getGoodsInfoList();
		if(goodsInfoList != null){
			for(GoodsInfo goods:goodsInfoList){
				String[] goodsNo = goods.getGoodsNo();
				String[] goodsName = goods.getGoodsName();
				String[] isvGoodsNo = goods.getIsvGoodsNo();
				String[] abbreviation = goods.getAbbreviation();
				for(String no:goodsNo){
					System.out.println(no);
				}
				for(String name:goodsName){
					System.out.println(name);
				}
				for(String isv:isvGoodsNo){
					System.out.println(isv);
				}
				for(String abb:abbreviation){
					System.out.println(abb);
				}
			}
		}
	}
	
//	修改主商品信息
	@Test
	public void updateJdGoods() throws JdException {
		EclpGoodsUpdateGoodsInfoRequest request=new EclpGoodsUpdateGoodsInfoRequest();
		request.setGoodsNo("EMG4418046784201");
		request.setAbbreviation("U41MV2H1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt = sdf.format(new Date());
		request.setTimestamp(dt);
		DefaultJdClient client = new DefaultJdClient(SERVER_URL,ACCESS_TOKEN,APP_KEY,APP_SECRET);
		EclpGoodsUpdateGoodsInfoResponse response=client.execute(request);
		System.out.println(response.getUpdateResult());
//		return response.getUpdateResult();
	}
	
//	入库单下发
	@Test
	public void addStoreIn() throws JdException {
		EclpPoAddPoOrderRequest request=new EclpPoAddPoOrderRequest();

		request.setSpPoOrderNo("201612130000000001");
		request.setDeptNo(DEPT_NO);
		request.setWhNo("");
		request.setSupplierNo("lefuzhifu");
		request.setDeptGoodsNo("EMG4418046784201");
		request.setNumApplication("1");
		request.setGoodsStatus("1");

		DefaultJdClient client = new DefaultJdClient(SERVER_URL,ACCESS_TOKEN,APP_KEY,APP_SECRET);
		EclpPoAddPoOrderResponse response=client.execute(request);
		String poOrderNo = response.getPoOrderNo();
		if(poOrderNo == null || "".equals(poOrderNo.trim())){
			throw new JdException("京东采购入库单下发失败!");
		}
		System.out.println(poOrderNo);
	}
	
//	查询入库单信息
	@Test
	public void findPoOrder() throws JdException {
		EclpPoQueryPoOrderRequest request=new EclpPoQueryPoOrderRequest();
		request.setPoOrderNo("");

		DefaultJdClient client = new DefaultJdClient(SERVER_URL,ACCESS_TOKEN,APP_KEY,APP_SECRET);
		EclpPoQueryPoOrderResponse response=client.execute(request);
		List<QueryPoModel> queryPoModelList = response.getQueryPoModelList();
		if(queryPoModelList != null){
			for(QueryPoModel qpm : queryPoModelList){
				System.out.print(qpm.getPoOrderNo()+"::");
				System.out.print(qpm.getIsvPoOrderNo()+"::");
				System.out.print(qpm.getMsg()+"::");
				System.out.print(qpm.getPoOrderStatus()+"::");
				System.out.print(qpm.getResultCode()+"::");
				System.out.print(qpm.getStorageStatus()+"::");
				System.out.print(qpm.getWhNo()+"::");
				List<PoItemModel> poItemModelList = qpm.getPoItemModelList();
				if(poItemModelList != null){
					for(PoItemModel pim : poItemModelList){
						System.out.print(pim.getGoodsNo()+"::");
						System.out.print(pim.getGoodsStatus()+"::");
						System.out.print(pim.getNumApplication()+"::");
						System.out.print(pim.getRealInstoreQty());
					}
				}
				System.out.println();
			}
		}
//		return response.getQueryPoModelList();
	}
	
//	取消入库单
	@Test
	public void cancalPoOrder() throws JdException{
		EclpPoCancalPoOrderRequest request=new EclpPoCancalPoOrderRequest();

		request.setPoOrderNo("");
		DefaultJdClient client = new DefaultJdClient(SERVER_URL,ACCESS_TOKEN,APP_KEY,APP_SECRET);
		EclpPoCancalPoOrderResponse response=client.execute(request);
		String code = response.getCode();
		System.out.println(code);
//		return response.getCode();
	}
	
//	查询库房信息
	@Test
	public void findWareHouse() throws JdException{
		JdClient client=new DefaultJdClient(SERVER_URL,ACCESS_TOKEN,APP_KEY,APP_SECRET);

		EclpMasterQueryWarehouseRequest request=new EclpMasterQueryWarehouseRequest();

		request.setDeptNo(DEPT_NO);
//		request.setWarehouseNos( "jingdong" );
//		request.setStatus( "jingdong" );

		EclpMasterQueryWarehouseResponse response=client.execute(request);
		List<WarehouseOut> querywarehouseResult = response.getQuerywarehouseResult();
		if(querywarehouseResult != null){
			for(WarehouseOut warehouse:querywarehouseResult){
				System.out.print(warehouse.getWarehouseNo()+"::");
				System.out.print(warehouse.getWarehouseName()+"::");
				System.out.print(warehouse.getProvince()+"::");
				System.out.print(warehouse.getCity()+"::");
				System.out.print(warehouse.getCounty()+"::");
				System.out.print(warehouse.getTown()+"::");
				System.out.print(warehouse.getAddress());
				
				System.out.println();
			}
		}
	}
}
