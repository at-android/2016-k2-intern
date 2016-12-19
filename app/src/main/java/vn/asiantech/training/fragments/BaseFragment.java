package vn.asiantech.training.fragments;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by phuong on 16/12/2016.
 */
@EFragment
public abstract class BaseFragment extends Fragment{
    @AfterViews
    abstract void inits();
}
