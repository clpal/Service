package demo.servicedemoipc.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import demo.servicedemoipc.ui.main.RandNumService.LocalBinder;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import demo.servicedemoipc.R;
/**
 * This is a very simple example of a service and Ibinder (IPC)
 * when the user clicks the button, it retrieves a random number from the service.
 * <p>
 * http://developer.android.com/guide/components/bound-services.html
 * http://developer.android.com/reference/android/app/Service.html
 */
public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    RandNumService mService;
    boolean mBound = false;

    public static MainFragment newInstance() {
        return new MainFragment();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(getActivity(), RandNumService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            getActivity().unbindService(mConnection);
            mBound = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.main_fragment, container, false);

        //setup the button, so it will call into the service and get a random number.
        myView.findViewById(R.id.btn_gn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBound) {
                    // Call a method from the RandNumService.
                    // However, if this call were something that might hang, then this request should
                    // occur in a separate thread to avoid slowing down the activity performance.
                    //but getting a random number is really quick, so I'm doing it here instead.
                    int num = mService.getRandomNumber();
                    Toast.makeText(getActivity(), "number: " + num, Toast.LENGTH_SHORT).show();
                }

            }
        });
        return myView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }
    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
           LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
            mBound = false;
        }
    };
}