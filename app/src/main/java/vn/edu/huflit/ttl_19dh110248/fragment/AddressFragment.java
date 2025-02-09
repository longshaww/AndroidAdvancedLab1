package vn.edu.huflit.ttl_19dh110248.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import vn.edu.huflit.ttl_19dh110248.PermissionTask;
import vn.edu.huflit.ttl_19dh110248.R;
import vn.edu.huflit.ttl_19dh110248.locationSearvice.LocationServiceTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragment extends Fragment {
    Button bntNext;
    NavController navController;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = new Bundle();
        bntNext = view.findViewById(R.id.button2);
        navController = Navigation.findNavController(view);
        TextInputLayout address=view.findViewById(R.id.address);
        TextInputLayout phone=view.findViewById(R.id.phone);
        bntNext.setOnClickListener(v -> {
            LatLng latLng = LocationServiceTask.getLatLngFromAddress(getContext(), address.getEditText().getText().toString());
            String Firstname = getArguments().getString("Firstname");
            String Lastname= getArguments().getString("Lastname");
            String Phone=phone.getEditText().getText().toString();
            String Address=address.getEditText().getText().toString();

            if(Address.isEmpty()||Phone.isEmpty()){
                Snackbar.make(view,"Vui lòng nhập đầy đủ thông tin!",Snackbar.LENGTH_LONG).show();
                return;
            }
            else{
                bundle.putString("Firstname",Firstname);
                bundle.putString("Lastname",Lastname);
                bundle.putString("Address", Address);
                bundle.putString("Phone", Phone);
                bundle.putDouble("latitude", latLng.latitude);
                bundle.putDouble("longitude", latLng.longitude);
            }
            navController.navigate(R.id.action_addressFragment_to_username_PasswordFragment,bundle);
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        if (LocationServiceTask.isLocationServiceEnabled(getActivity())) {
            if (PermissionTask.isLocationServiceAllowed(getActivity()))
                getLastLocation(getActivity());
            else
                PermissionTask.requestLocationServicePermissions(getActivity());
        } else {
            LocationServiceTask.displayEnableLocationServiceDialog(getActivity());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionTask.LOCATION_SERVICE_REQUEST_CODE && grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLastLocation(getActivity());
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void getLastLocation(Context context) {

    }
}