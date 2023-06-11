package com.example.medical_platform_android.ui.side_menu;

import static com.baidu.location.LocationClient.setAgreePrivacy;

import android.app.NativeActivity;
import android.content.Context;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;

import java.util.HashMap;


public class AddressFragment extends Fragment  implements OnGetGeoCoderResultListener {
    private View rootView;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private EditText etDetailedAddress;
    private Button btnSave;
    private LocationClient mLocationClient ;

    private TextView mtextView,text_address;
    // 是否是第一次定位
    private boolean isFirstLocate = true;
    // 当前定位模式
    private MyLocationConfiguration.LocationMode locationMode;

    private MyLocationListener myLocationListener;


    GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用


    public AddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_address, container, false);
        SDKInitializer.initialize(getContext());
//
//        Log.d("TAG", "onCreateView: 1111111111111111111111111111111");
        initView();


        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);


        setClickListener();

        return rootView;
    }


    private void setClickListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addressStr=etDetailedAddress.getText().toString()+mtextView.getText().toString();
                if (etDetailedAddress.getText().toString()==null
                        ||etDetailedAddress.getText().toString()==""){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "详细地址不能为空", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                if(SPUtil.loginStar(getContext())){
                    int userid=Integer.parseInt(SPUtil.getString(getContext(),"userid"));
                    HashMap<String,Object> t=new HashMap<>();
                    t.put("userid",userid);
                    t.put("b",mtextView.getText().toString()+etDetailedAddress.getText().toString());
                    OkhttpUtil.postRequestWithToken(getContext(),UrlConstants.xzw_url + "users/setB",t, new ResponseCallback() {
                        @Override
                        public void response(String res) {

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    text_address.setText(t.get("b").toString());
                                    Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void failure(Exception e) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "提交失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

//                    text_address.getText().toString()
                }else{
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "还未登录", Toast.LENGTH_SHORT).show();
                        }
                    });
                }




            }
        });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {


                reverseSearch(latLng);
            }

            @Override
            public void onMapPoiClick(MapPoi mapPoi) {

            }
        });

    }
    /**
     * 反向搜索
     * @param latLng
     */
    public void reverseSearch(LatLng latLng)
    {
        mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                .location(latLng));
    }


    private void initView() {
        text_address=rootView.findViewById(R.id.text_address);
        btnSave=rootView.findViewById(R.id.btnSave);
        etDetailedAddress=rootView.findViewById(R.id.etDetailedAddress);
//获取地图控件引用
        mMapView = rootView.findViewById(R.id.bmapView);
//获取文本显示控件
        mtextView = rootView.findViewById(R.id.text_tishi);
// 得到地图
        mBaiduMap = mMapView.getMap();
// 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
//定位初始化
        setAgreePrivacy(true);


        mMapView.removeViewAt(1);

        mLocationClient = new LocationClient(getContext());

//通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
// 可选，设置地址信息
        option.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        option.setIsNeedLocationDescribe(true);

//设置locationClientOption
        mLocationClient.setLocOption(option);

//同意隐私协议
//        mLocationClient.setAgreeProtocol(true);

//注册LocationListener监听器
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
        mLocationClient.start();

    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        Log.d("TAG", "onGetGeoCodeResult: --------------------1111111111111111");
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getContext(), "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mBaiduMap.clear();
        mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka)));
        //加上覆盖物
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                .getLocation()));
        //定位
        String strInfo = String.format("纬度：%f 经度：%f",
                result.getLocation().latitude, result.getLocation().longitude);
//        Toast.makeText(getContext(), strInfo, Toast.LENGTH_LONG).show();
        //result保存地理编码的结果 城市-->坐标


    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        Log.d("TAG", "onGetGeoCodeResult: --------------------1111111111111111");
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getContext(), "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mBaiduMap.clear();
        mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka)));
        //加上覆盖物
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                .getLocation()));
        //定位
        Toast.makeText(getContext(), result.getAddress(),
                Toast.LENGTH_LONG).show();

        mtextView.setText(result.getAddress());

        //result保存翻地理编码的结果 坐标-->城市
    }

    // 继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView
    public class MyLocationListener extends BDAbstractLocationListener {



        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }

            // 如果是第一次定位
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            if (isFirstLocate) {
                isFirstLocate = false;
                //给地图设置状态
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(ll));
                // 显示当前信息
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\n国家：" + location.getCountry());
                stringBuilder.append("\n城市："+ location.getCity());
                stringBuilder.append("\n区：" + location.getDistrict());
                stringBuilder.append("\n街道：" + location.getStreet());
                stringBuilder.append("\n地址：" + location.getAddrStr());

                mtextView.setText(stringBuilder.toString());
            }

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            // ------------------  以下是可选部分 ------------------
            // 自定义地图样式，可选
            // 更换定位图标，这里的图片是放在 drawble 文件下的
            BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_my_marka);
            // 定位模式 地图SDK支持三种定位模式：NORMAL（普通态）, FOLLOWING（跟随态）, COMPASS（罗盘态）
            locationMode = MyLocationConfiguration.LocationMode.NORMAL;
            // 定位模式、是否开启方向、设置自定义定位图标、精度圈填充颜色以及精度圈边框颜色5个属性（此处只设置了前三个）。
            MyLocationConfiguration mLocationConfiguration = new MyLocationConfiguration(locationMode,true,mCurrentMarker);
            // 使自定义的配置生效
            mBaiduMap.setMyLocationConfiguration(mLocationConfiguration);
            // ------------------  可选部分结束 ------------------


        }
    }



    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroyView();
    }


}