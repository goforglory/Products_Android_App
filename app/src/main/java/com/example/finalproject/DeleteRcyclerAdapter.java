package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeleteRcyclerAdapter extends RecyclerView.Adapter<DeleteRcyclerAdapter.ProductsViewHolder> {
//    private DeleteRcyclerAdapter.Mypro pro;

    ArrayList<Products> products;
    Context context;
    public DeleteRcyclerAdapter (ArrayList<Products> products , Context context){
       this.context = context;
       this.products = products;

    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_reycler_delete , null , false);
        ProductsViewHolder holder = new ProductsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        Products myp = products.get(position);
        holder.tv_id_data.setText(String.valueOf(myp.getId()));
        holder.tv_available_quantity_data.setText(String.valueOf(myp.getAvailablequantity()));
        holder.tv_cat_data.setText(myp.getCategory());
        holder.tv_purchase_data.setText(String.valueOf(myp.getPurchaseprice()));
        holder.tv_quantityperunit_data.setText(myp.getQuantityperunit());
        holder.tv_TM_data.setText(myp.getTrademark());
        holder.tv_selling_data.setText(String.valueOf(myp.getSellingprice()));



    }

    @Override
    public int getItemCount() {
        return products.size();
    }

//    public interface Mypro {
//
//        void Mypro (Products p1);
//
//    }
    class ProductsViewHolder extends RecyclerView.ViewHolder{
        TextView tv_id_data ;
        TextView tv_cat_data;
        TextView tv_TM_data ;
        TextView tv_selling_data ;
        TextView tv_purchase_data ;
        TextView tv_quantityperunit_data ;
        TextView tv_available_quantity_data ;
        Button btn_edit;
        public ProductsViewHolder(@NonNull View itemView) {

            super(itemView);

//        tv_id_data = itemView.findViewById(R.id.custom_tv_id_data);
//        tv_cat_data = itemView.findViewById(R.id.custom_tv_cat_data);
//        tv_TM_data = itemView.findViewById(R.id.custom_tv_TM_data);
//        tv_selling_data = itemView.findViewById(R.id.custom_tv_selling_price_data);
//        tv_purchase_data = itemView.findViewById(R.id.custom_tv_purchase_price_data);
//        tv_quantityperunit_data = itemView.findViewById(R.id.custom_tv_quantityperunit_data);
//        tv_available_quantity_data = itemView.findViewById(R.id.custom_tv_available_quantity_data);

            tv_id_data = itemView.findViewById(R.id.recycle_products_id_data);
            tv_cat_data = itemView.findViewById(R.id.recycle_products_cat_data);
            tv_TM_data = itemView.findViewById(R.id.recycle_products_tm_data);
            tv_selling_data = itemView.findViewById(R.id.recycle_products_selling_data);
            tv_purchase_data = itemView.findViewById(R.id.recycle_products_purchase_data);
            tv_quantityperunit_data = itemView.findViewById(R.id.recycle_products_quantityperunit_data);
            tv_available_quantity_data = itemView.findViewById(R.id.recycle_products_available_data);
            itemView.findViewById(R.id.recycle_products_btn_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(context);
                    dataBaseAccess.Open();

                    Products p = products.get(getAdapterPosition());
                    dataBaseAccess.DeleteProduct(p);
                    dataBaseAccess.Close();
                    Toast.makeText(context , "Deleted successfully" , Toast.LENGTH_LONG).show();
//                    pro.Mypro(products.get(getAdapterPosition()));
                }
            });



        }
    }

}
