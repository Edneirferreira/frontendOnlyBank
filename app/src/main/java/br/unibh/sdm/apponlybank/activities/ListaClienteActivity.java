package br.unibh.sdm.apponlybank.activities;

import static br.unibh.sdm.apponlybank.R.id.listViewListaClientes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.unibh.sdm.apponlybank.R;
import br.unibh.sdm.apponlybank.api.ClienteService;
import br.unibh.sdm.apponlybank.api.RestServiceGenerator;
import br.unibh.sdm.apponlybank.entidades.Clientes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaClienteActivity extends AppCompatActivity {

    private ClienteService service = null;
    private final ListaClienteActivity listaClienteActivity = this;
    private final Context context;

    public ListaClienteActivity() {
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Clientes");
        setContentView(R.layout.activity_lista_clientes);
        service = RestServiceGenerator.createService(ClienteService.class);
        criaAcaoBotaoFlutuante();
        criaAcaoCliqueLongo();
    }

    private void criaAcaoCliqueLongo() {
        ListView listView = findViewById(R.id.listViewListaClientes);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ListaClienteActivity","Clicou em clique longo na posicao "+position);
                final Clientes objetoSelecionado = (Clientes) parent.getAdapter().getItem(position);
                Log.i("ListaClienteActivity", "Selecionou o cliente "+objetoSelecionado.getId());
                new AlertDialog.Builder(parent.getContext()).setTitle("Removendo Cliente")
                        .setMessage("Tem certeza que deseja remover o cliente de nome: "+objetoSelecionado.getNome()+"?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                removeCliente(objetoSelecionado);
                            }
                        }).setNegativeButton("Não", null).show();
                return true;
            }
        });
    }

    private void removeCliente(Clientes clientes) {
        Call<Boolean> call = null;
        Log.i("ListaClienteActivity","Vai remover o cliente: "+ clientes.getNome());
        call = this.service.excluiCliente(clientes.getId());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Log.i("ListaClienteActivity", "Cliente removido com sucesso: " + clientes.getNome());
                    Toast.makeText(getApplicationContext(), "Cliente removido com sucesso: " + clientes.getNome(), Toast.LENGTH_LONG).show();
                    onResume();
                } else {
                    Log.e("ListaClienteActivity", "Erro (" + response.code()+"): Verifique novamente os valores");
                    Toast.makeText(getApplicationContext(), "Erro (" + response.code()+"): Verifique novamente os valores", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("ListaClienteActivity", "Erro: " + t.getMessage());
            }
        });
    }

    private void criaAcaoBotaoFlutuante() {
        FloatingActionButton botaoNovo = findViewById(R.id.floatingActionButtonCriar);
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ListaClienteActivity","Clicou no botão para adicionar novo cliente");
                startActivity(new Intent(ListaClienteActivity.this,
                        FormularioClienteActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscaCliente();
    }

    public void buscaCliente(){
        Call<List<Clientes>> call = this.service.getCliente();
        call.enqueue(new Callback<List<Clientes>>() {
            @Override
            public void onResponse(Call<List<Clientes>> call, Response<List<Clientes>> response) {
                if (response.isSuccessful()) {
                    Log.i("ListaClienteActivity", "Retornou " + response.body().size() + " Clientes!");
                    ListView listView = findViewById(R.id.listViewListaClientes);
                    listView.setAdapter(new ListaClienteAdapter(context,response.body()));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("ListaClienteActivity", "Selecionou o objeto de posicao "+position);
                            Clientes objetoSelecionado = (Clientes) parent.getAdapter().getItem(position);
                            Log.i("ListaClienteActivity", "Selecionou o cliente "+objetoSelecionado.getId());
                            Intent intent = new Intent(ListaClienteActivity.this, FormularioClienteActivity.class);
                            intent.putExtra("objeto", objetoSelecionado);
                            startActivity(intent);
                        }
                    });
                } else {
                    Log.e("ListaClienteActivity", "" + response.message());
                    Toast.makeText(getApplicationContext(), "Erro (" + response.code()+"): "+ response.message(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Clientes>> call, Throwable t) {
                Log.e("ListaClienteActivity", "Erro: " + t.getMessage());
            }
        });
    }
}