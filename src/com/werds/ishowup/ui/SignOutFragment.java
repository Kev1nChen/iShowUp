package com.werds.ishowup.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werds.ishowup.R;

public class SignOutFragment extends Fragment {
	
	public SignOutFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_sign_out, container, false);
         
        return rootView;
    }
}
