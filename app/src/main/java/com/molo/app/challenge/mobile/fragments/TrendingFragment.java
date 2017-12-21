package com.molo.app.challenge.mobile.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import com.molo.app.challenge.mobile.R;
import com.molo.app.challenge.mobile.adapters.RepositoryListAdapter;
import com.molo.app.challenge.mobile.models.Owner;
import com.molo.app.challenge.mobile.models.Repository;
import com.molo.app.challenge.mobile.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrendingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingFragment extends Fragment {
    private RepositoryListAdapter repositoryListAdapter;
    private ListView repositoryList;
    private OnFragmentInteractionListener mListener;

    public TrendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TrendingFragment.
     */
    public static TrendingFragment newInstance() {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        //add initialisation params here if any
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           //get initialisation args
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_trending, container, false);
        repositoryList=v.findViewById(R.id.repository_list);
        repositoryListAdapter=new RepositoryListAdapter(getContext());
        //for test case
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryList.setAdapter(repositoryListAdapter);
        //test of http method
        Utils.http(getContext(), "https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc", new Utils.HttpResultAdapter() {
            @Override
            public void onSuccess(String jsonString) {
                super.onSuccess(jsonString);
                Log.e("TrendingFrag.CreateV",jsonString);
            }

            @Override
            public void onFail(String err) {
                super.onFail(err);
                Log.e("TrendingFrag.CreateV",err);
            }
        });
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }
}
