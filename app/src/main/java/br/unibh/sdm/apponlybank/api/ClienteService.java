package br.unibh.sdm.apponlybank.api;

import java.util.List;

import br.unibh.sdm.apponlybank.entidades.Clientes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteService {

    @Headers({
            "Accept: application/json",
            "User-Agent: AppOnlyBank"
    })
    @GET("cliente")
    Call<List<Clientes>> getCliente();

    @GET("cliente/{id}")
    Call<Clientes> getCliente(@Path("id") String id);

    @POST("cliente")
    Call<Clientes> criaCliente(@Body Clientes clientes);

    @PUT("cliente/{id}")
    Call<Clientes> atualizaCliente(@Path("id") String id, @Body Clientes clientes);

    @DELETE("cliente/{id}")
    Call<Boolean> excluiCliente(@Path("id") String id);

}
