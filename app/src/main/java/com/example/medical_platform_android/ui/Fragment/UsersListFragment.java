package com.example.medical_platform_android.ui.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.activity.Login;
import com.example.medical_platform_android.activity.ManagerHome;
import com.example.medical_platform_android.activity.Register;
import com.example.medical_platform_android.activity.UserList_Details;
import com.example.medical_platform_android.adapter.UsersLsitAdapter;
import com.example.medical_platform_android.entity.If_ExitResponse;
import com.example.medical_platform_android.entity.RegisterResponse;
import com.example.medical_platform_android.entity.UserListResponse;
import com.example.medical_platform_android.entity.Users;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UsersListFragment extends Fragment {
    private ImageView search,back,AddUser_List,AddHeaderImage;
    private TextView search_username,search_name,AddUsername,AddName,AddPassword;
    private DatePicker AddBirthdate;
    private RadioGroup radioGroup,AddRadioGroup;
    private RadioButton radioButton,AddRadioButton;
    private static final String TAG="UserListFragment";
    private View rootView,AddUserView;
    private RecyclerView rvUserList;
    private UsersLsitAdapter usersListAdapter;
    private List<Users> usersList;
    private Button Adduser;
    private String selectedValue,Username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_users_list, container, false);
        initView();
        getDataFromNet();
        setListeners();
        return rootView;
    }

    private void setListeners() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resultId = radioGroup.getCheckedRadioButtonId();
                System.out.println(resultId);
                radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                String Gender = radioButton.getText().toString();
                if(resultId == 3) {
                    Gender = "";
                }
                String Username = search_username.getText().toString();
                String Name = search_name.getText().toString();
                System.out.println(Username + Name + Gender);
                String url = UrlConstants.findAllUser_url;
                Map<String,Object> params = new HashMap<>();
                params.put("username",Username);
                params.put("name",Name);
                params.put("gender",Gender);
                System.out.println(Username + Name + Gender);
                //usersListAdapter.setUsersList(null);
                OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                    @Override
                    public void response(String res) {
                        Log.d(TAG,"res: " + res);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                UserListResponse userListResponse = new Gson().fromJson(res,UserListResponse.class);
                                if(userListResponse !=null & userListResponse.getCode() == 200) {
                                    usersList = userListResponse.getUsersList();
                                    Log.d(TAG,"usersList:" + userListResponse.getUsersList());
                                    usersListAdapter.setUsersListData(usersList);
                                    Toast.makeText(getActivity(), "搜索成功", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getActivity(), "没有查询到数据", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void failure(Exception e) {

                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ManagerHome.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        AddUser_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                AddUserView = LayoutInflater.from(getContext()).inflate(R.layout.add_userslist,null);
                AddUsername = AddUserView.findViewById(R.id.AddUser_Username);
                AddName = AddUserView.findViewById(R.id.AddUser_name);
                AddPassword = AddUserView.findViewById(R.id.AddUser_Password);
                AddRadioGroup = AddUserView.findViewById(R.id.AddUser_gender_selected);
                AddBirthdate = AddUserView.findViewById(R.id.AddUser_birthdate);
                AddHeaderImage = AddUserView.findViewById(R.id.AddUser_img_item_images);
                Adduser = AddUserView.findViewById(R.id.AddUser);
                builder.setView(AddUserView);
                builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                AddHeaderImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<String> data = new ArrayList<>();
                        data.add("图片1");
                        data.add("图片2");
                        data.add("图片3");
                        data.add("图片4");
                        data.add("图片5");
                        data.add("图片6");
                        data.add("图片7");
                        data.add("图片8");
                        data.add("图片x");
                        data.add("图片y");
                        data.add("图片z");
// 创建AlertDialog.Builder对象
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

// 创建下拉框控件
                        Spinner spinner = new Spinner(getContext());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, data);
                        spinner.setAdapter(adapter);

// 设置弹出框的标题，消息和下拉框控件
                        builder.setTitle("头像");
                        builder.setMessage("选择一个喜欢的头像吧");
                        builder.setView(spinner);

// 设置弹出框的确定按钮
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取选中的下拉框项
                                int position = spinner.getSelectedItemPosition();
                                selectedValue = data.get(position);
                                Glide.with(getContext()).load("http://192.168.246.1:8081/upload/images/" + selectedValue.charAt(2) + ".jpg").into(AddHeaderImage);
//                        int resourceId = getResources().getIdentifier(selectedValue.charAt(2) + "", "drawable", getPackageName());
//                        headImage.setImageResource(resourceId);
                                // 执行操作
                                // ...
                            }
                        });
// 设置弹出框的取消按钮
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 执行操作
                                // ...
                            }
                        });

// 创建AlertDialog对象并显示
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                Adduser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int result = AddRadioGroup.getCheckedRadioButtonId();
                        AddRadioButton = AddRadioGroup.findViewById(result);
                        String Month = judgeNumber(AddBirthdate.getMonth());
                        String date = judgeNumber(AddBirthdate.getDayOfMonth());
                        String birthdate = AddBirthdate.getYear()+"-"+Month+"-"+date;
                        Username = AddUsername.getText().toString();
                        String Name = AddName.getText().toString();
                        String Password = AddPassword.getText().toString();
                        String Gender = AddRadioButton.getText().toString();
                        if(Username.equals("") || Password.equals("") || Name.equals("") || Gender.equals("")) {
                            Toast.makeText(getContext(), "请完善信息", Toast.LENGTH_SHORT).show();
                        }else {
                            String url = UrlConstants.register_url;
                            Map<String,Object> params = new HashMap<>();
                            params.put("username",Username);
                            params.put("password",Password);
                            params.put("name",Name);
                            params.put("birthdate",birthdate);
                            params.put("gender",Gender);
                            params.put("headImage","http://192.168.246.1:8081/upload/images/" + selectedValue.charAt(2) + ".jpg");
                            OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                                @Override
                                public void response(String res) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            RegisterResponse registerResponse = new Gson().fromJson(res, RegisterResponse.class);
                                            if(registerResponse != null && registerResponse.getCode() == 200) {
                                                getDataFromNet();
                                                //usersListAdapter.setUsersList(usersList);
                                                AddRole();
                                                Toast.makeText(getContext(), registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(getContext(), registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void failure(Exception e) {

                                }
                            });
                        }
                    }
                });

            }
        });
    }

    private void AddRole() {
        String url = UrlConstants.users_IfExit;
        Map<String,Object> params = new HashMap<>();
        params.put("username",Username);
        System.out.println(Username);
        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
            @Override
            public void response(String res) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        If_ExitResponse if_exitResponse = new Gson().fromJson(res,If_ExitResponse.class);
                        System.out.println(if_exitResponse.toString());
                        if(if_exitResponse != null && if_exitResponse.getCode() == 200) {
                            System.out.println(if_exitResponse);
                            Users users = if_exitResponse.getUser();
                            String url = UrlConstants.AddUserRole;
                            Map<String,Object> params = new HashMap<>();
                            params.put("userId",users.getId());
                            params.put("roleId",4);
                            OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                                @Override
                                public void response(String res) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            RegisterResponse registerResponse = new Gson().fromJson(res,RegisterResponse.class);
                                            if (registerResponse != null && registerResponse.getCode() == 200) {
                                                Toast.makeText(getContext(), registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void failure(Exception e) {

                                }
                            });
                        }

                    }
                });
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }

    private void getDataFromNet() {
        String url = UrlConstants.findAllUser_url;
        Map<String,Object> params = new HashMap<>();
        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
            @Override
            public void response(String res) {
                Log.d(TAG,"res: " + res);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UserListResponse userListResponse = new Gson().fromJson(res,UserListResponse.class);
                        if(userListResponse !=null & userListResponse.getCode() == 200) {
                            usersList = userListResponse.getUsersList();
                            Log.d(TAG,"usersList:" + usersList);
                            usersListAdapter.setUsersListData(usersList);
                        }else{
                            Toast.makeText(getActivity(), "没有查询到数据", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }
    private String judgeNumber(int x) {
        if(x < 10) {
            return "0" + x;
        }else{
            return x + "";
        }
    }

    private void initView() {
        rvUserList = rootView.findViewById(R.id.UserList);
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvUserList.setLayoutManager(linearLayoutManager);
        usersListAdapter = new UsersLsitAdapter(getActivity());
        usersListAdapter.setMyOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
                Intent intent = new Intent(getActivity(), UserList_Details.class);
                Users users = usersList.get(position);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });
        rvUserList.setAdapter(usersListAdapter);

        search_username = rootView.findViewById(R.id.Search_username);
        search_name = rootView.findViewById(R.id.Search_name);
        radioGroup = rootView.findViewById(R.id.Search_gender);
//        int radioButtonID = radioGroup.getCheckedRadioButtonId();
//        radioButton = radioGroup.findViewById(radioButtonID);
        search = rootView.findViewById(R.id.Search);
        back = rootView.findViewById(R.id.UserList_back);
        AddUser_List = rootView.findViewById(R.id.UserList_AddUser);
    }
}