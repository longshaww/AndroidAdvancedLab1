package vn.edu.huflit.ttl_19dh110248.fragment;

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

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import vn.edu.huflit.ttl_19dh110248.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FullNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FullNameFragment extends Fragment {
    Button bntNext;
    NavController navController;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FullNameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FullNameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FullNameFragment newInstance(String param1, String param2) {
        FullNameFragment fragment = new FullNameFragment();
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
        return inflater.inflate(R.layout.fragment_full_name, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=new Bundle();
        bntNext = view.findViewById(R.id.button);
        navController = Navigation.findNavController(view);
        TextInputLayout firstName=view.findViewById(R.id.firstName);
        TextInputLayout lastName=view.findViewById(R.id.lastName);
        bntNext.setOnClickListener(v -> {
            String Firstname=firstName.getEditText().getText().toString();
            String Lastname=lastName.getEditText().getText().toString();
            if(Firstname.isEmpty()||Lastname.isEmpty()){
                Snackbar.make(view,"    Vui lòng nhập đầy đủ thông tin!",Snackbar.LENGTH_LONG).show();
                return;
            }
            else{
                bundle.putString("Firstname", Firstname);
                bundle.putString("Lastname", Lastname);
            }
            navController.navigate(R.id.action_fullNameFragment_to_addressFragment,bundle);
        });
    }
}