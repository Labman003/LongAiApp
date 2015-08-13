package com.ouzhouren.longai.module.search;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.entity.User;
import com.ouzhouren.longai.module.member.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    public static List<User> users = new ArrayList<User>();
    private Activity mAc;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mAc = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        // Setup the gridview and te adapter
        GridView gridview = (GridView) rootView.findViewById(R.id.user_grid);
        gridview.setOnItemClickListener(userClickListener);
        for (int i = 0; i < 8; i++) {
            User user = new User();
            user.setName("彭大狗");
            user.setUserName("霸业蘑菇");
            user.setEmail("pengshizhong@dogdog.com");
            user.setGender("男");
            user.setPhone("18508235983");
            user.setPicture("http://img3.douban.com/view/photo/photo/public/p2260397105.jpg");
            users.add(user);
            User user2 = new User();
            user2.setName("彭二狗");
            user2.setUserName("霸业蘑菇");
            user2.setEmail("pengshizhong@dogdog.com");
            user2.setGender("男");
            user2.setPhone("18508235983");
            user2.setPicture("http://img4.douban.com/view/photo/photo/public/p2257056746.jpg");
            users.add(user2);
            User user3 = new User();
            user3.setName("彭三狗");
            user3.setUserName("霸业蘑菇");
            user3.setEmail("pengshizhong@dogdog.com");
            user3.setGender("男");
            user3.setPhone("18508235983");
            user3.setPicture("http://img3.douban.com/view/photo/photo/public/p897018060.jpg");
            users.add(user3);
            User user4 = new User();
            user4.setName("彭四狗");
            user4.setUserName("霸业蘑菇");
            user4.setEmail("pengshizhong@dogdog.com");
            user4.setGender("男");
            user4.setPhone("18508235983");
            user4.setPicture("http://img3.douban.com/view/photo/photo/public/p2260762961.jpg");
            users.add(user4);
            User user5 = new User();
            user5.setName("彭小狗");
            user5.setUserName("霸业蘑菇");
            user5.setEmail("pengshizhong@dogdog.com");
            user5.setGender("男");
            user5.setPhone("18508235983");
            user5.setPicture("http://img4.douban.com/view/photo/photo/public/p2257063766.jpg");
            users.add(user5);
            User user6 = new User();
            user6.setName("彭老狗");
            user6.setUserName("霸业蘑菇");
            user6.setEmail("pengshizhong@dogdog.com");
            user6.setGender("男");
            user6.setPhone("18508235983");
            user6.setPicture("http://img3.douban.com/view/status/median/public/9ddb6a728b7b053.jpg");
            users.add(user6);

        }
        gridview.setAdapter(new UserAdapter(mAc, users));
        // Inflate the layout for this fragment
        return rootView;
    }
    // Adapter OnItemClick event
    private AdapterView.OnItemClickListener userClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent detailIntent = new Intent(mAc, DetailActivity.class);
    //        detailIntent.putExtra("users", (ArrayList<User>)users);
            detailIntent.putExtra("position", i);

//            ImageView userImage = (ImageView) view.findViewById(R.id.user_image);
//            //  ((ViewGroup) userImage.getParent()).setTransitionGroup(false);
//
//            ACache mCache = ACache.get(mAc);
//            mCache.put(String.valueOf(R.drawable.header), ((BitmapDrawable) userImage.getDrawable()).getBitmap());
//
//            // Setup the transition to the detail activity
//              //  ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(mAc, view, "photo" + i);
//              // mAc.startActivity(detailIntent, options.toBundle());
//
            startActivity(detailIntent);
        }
    };

}
