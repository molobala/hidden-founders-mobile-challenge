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
import android.widget.ProgressBar;
import com.android.volley.toolbox.StringRequest;
import com.molo.app.challenge.mobile.EndlessScrollListener;
import com.molo.app.challenge.mobile.R;
import com.molo.app.challenge.mobile.adapters.RepositoryListAdapter;
import com.molo.app.challenge.mobile.models.Owner;
import com.molo.app.challenge.mobile.models.Repository;
import com.molo.app.challenge.mobile.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrendingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingFragment extends Fragment implements Utils.HttpResultListener {
    private RepositoryListAdapter repositoryListAdapter;
    private ListView repositoryList;
    private OnFragmentInteractionListener mListener;
    private StringRequest request;
    private ProgressBar progressBar;
    private boolean inProgress=false;
    private int currentPage=1;
    private boolean hasMoreData=true;
    private static final String REPOSITORY_URL="https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc";
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
        progressBar=v.findViewById(R.id.progressBar);
        repositoryListAdapter=new RepositoryListAdapter(getContext());
        repositoryList.setAdapter(repositoryListAdapter);
        //for test case
       /* repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        repositoryListAdapter.add(new Repository(12,"molo-c-utils","une libraire ecrite en c",2452,new Owner("molobala","https://avatars2.githubusercontent.com/u/1728152?v=4",1)));
        */
        //test of http method
        /*Utils.http(getContext(), "https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc", new Utils.HttpResultAdapter() {
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
        });*/
        repositoryList.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                return loadMore();
            }
        });
        loadMore();
        //request=Utils.http(getContext(),REPOSITORY_URL,this);
        return v;
    }

    private boolean loadMore() {
        if(!hasMoreData || inProgress) return false;
        request=Utils.http(getContext(),REPOSITORY_URL+"&page="+currentPage++,this);
        inProgress=true;
        progressBar.setVisibility(View.VISIBLE);
        return true;
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

    @Override
    public void onSuccess(String jsonString) {
        Log.e("TrendinFrag.onSucc",jsonString);
        inProgress=false;
        progressBar.setVisibility(View.GONE);
        //parse the json string into an array of repositories
        try {
            JSONObject res=new JSONObject(jsonString);
            hasMoreData=!res.getBoolean("incomplete_results");
            JSONArray array= res.getJSONArray("items");
            for(int i=0;i<array.length();i++){
                JSONObject o=array.getJSONObject(i);
                //create new repo
                Repository rep=new Repository(o.getInt("id"),o.getString("name"),o.getString("description"),
                        o.getInt("stargazers_count"),null);
                o=o.getJSONObject("owner");
                Owner owner=new Owner(o.getString("login"),o.getString("avatar_url"),o.getInt("id"));
                rep.setOwner(owner);
                //add the repo to the adapter
                repositoryListAdapter.add(rep);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(String err) {
        inProgress=false;
        progressBar.setVisibility(View.GONE);
        Log.e("TrendinFrag.onSucc",err);
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
