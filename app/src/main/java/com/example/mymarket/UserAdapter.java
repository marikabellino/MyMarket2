
package com.example.mymarket;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mymarket.model.User;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomerViewHolder> {

    private List<User> customersList;
    private Context context;
    private FragmentManager fragmentManager;

    private String apiKey = "5277ff227ef6b66797d4c934025a2f8a";
    private String apiSecret = "02a7cef518d041d9732e05fc0b5e4430";
    private UserAdapter customerAdapter;

    public UserAdapter(List<User> customersList, Context context, FragmentManager fragmentManager) {
        this.customersList = customersList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_adapter, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        User customer = customersList.get(position);
        // holder.name.setText("Nome: " + customer.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return customersList.size();
    }

    static class CustomerViewHolder extends RecyclerView.ViewHolder {
     //   TextView name;


        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

          //  name = itemView.findViewById(R.id.name);


        }
    }
}

