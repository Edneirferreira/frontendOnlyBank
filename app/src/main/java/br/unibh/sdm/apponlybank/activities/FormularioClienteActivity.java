package br.unibh.sdm.apponlybank.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

import br.unibh.sdm.apponlybank.R;
import br.unibh.sdm.apponlybank.api.ClienteService;
import br.unibh.sdm.apponlybank.api.RestServiceGenerator;
import br.unibh.sdm.apponlybank.entidades.Clientes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormularioClienteActivity extends AppCompatActivity {

    private ClienteService service = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);
        setTitle("Edição do Cliente");
        service = RestServiceGenerator.createService(ClienteService.class);
        configuraBotaoSalvar();
        inicializaObjeto();
    }

    private void inicializaObjeto() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra("objeto") != null) {
            Clientes objeto = (Clientes) intent.getSerializableExtra("objeto");
            EditText codigo = findViewById(R.id.editTextCodigo);
            EditText nome = findViewById(R.id.editTextNome);
            EditText cpf = findViewById(R.id.editTextCpf);
            EditText dtNascimento = findViewById(R.id.editTextDtNascimento);
            EditText email = findViewById(R.id.editTextEmail);
            EditText endereco = findViewById(R.id.editTextEndereco);
            EditText estadoCivil = findViewById(R.id.editTextEstadoCivil);
            EditText renda = findViewById(R.id.editTextRenda);
            EditText rg = findViewById(R.id.editTextRg);
            EditText sexo = findViewById(R.id.editTextSexo);
            codigo.setText(objeto.getId());
            nome.setText(objeto.getNome());
            cpf.setText(objeto.getCpf());
            dtNascimento.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(objeto.getDtNascimento()));
            //dtNascimento.setText(objeto.getDtNascimento());
            email.setText(objeto.getEmail());
            endereco.setText(objeto.getEndereco());
            estadoCivil.setText(objeto.getEstadoCivil());
            renda.setText(objeto.getRenda().toString());
            rg.setText(objeto.getRg());
            sexo.setText(objeto.getSexo());
            codigo.setEnabled(false);
            Button botaoSalvar = findViewById(R.id.buttonSalvar);
            botaoSalvar.setText("Atualizar");
        }
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.buttonSalvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FormularioCliente","Clicou em Salvar");
                Clientes clientes = recuperaInformacoesFormulario();
                Intent intent = getIntent();
                if (intent.getSerializableExtra("objeto") != null) {
                    Clientes objeto = (Clientes) intent.getSerializableExtra("objeto");
                    clientes.setId(objeto.getId());
                }
            }
        });
    }

    private boolean validaFormulario(Clientes clientes){
        boolean valido = true;
        EditText codigo = findViewById(R.id.editTextCodigo);
        EditText nome = findViewById(R.id.editTextNome);
        EditText descricao = findViewById(R.id.editTextEmail);
        if (clientes.getId() == null || clientes.getId().trim().length() == 0){
            codigo.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            valido = false;
        } else {
            codigo.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_ATOP);
        }
        if (clientes.getNome() == null || clientes.getNome().trim().length() == 0){
            nome.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            valido = false;
        } else {
            nome.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_ATOP);
        }
        if (clientes.getCpf() == null || clientes.getCpf().trim().length() == 0){
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            valido = false;
        } else {
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_ATOP);
        }
        if (clientes.getEmail() == null || clientes.getEmail().trim().length() == 0){
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            valido = false;
        } else {
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_ATOP);
        }
        if (clientes.getEndereco() == null || clientes.getEndereco().trim().length() == 0){
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            valido = false;
        } else {
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_ATOP);
        }
        if (clientes.getEstadoCivil() == null || clientes.getEstadoCivil().trim().length() == 0){
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            valido = false;
        } else {
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_ATOP);
        }
        if (clientes.getRg() == null || clientes.getRg().trim().length() == 0){
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            valido = false;
        } else {
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_ATOP);
        }
        if (clientes.getSexo() == null || clientes.getSexo().trim().length() == 0){
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            valido = false;
        } else {
            descricao.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_ATOP);
        }
        if (!valido){
            Log.e("FormularioCliente", "Favor verificar os campos destacados");
            Toast.makeText(getApplicationContext(), "Favor verificar os campos destacados", Toast.LENGTH_LONG).show();
        }
        return valido;
    }

    private void salvarCliente(Clientes clientes) {
        Call<Clientes> call;
        Log.i("FormularioCliente","Vai criar o cliente: "+ clientes.getNome());
        call = service.criaCliente(clientes);
        call.enqueue(new Callback<Clientes>() {
            @Override
            public void onResponse(Call<Clientes> call, Response<Clientes> response) {
                if (response.isSuccessful()) {
                    Log.i("FormularioCliente", "Salvou o Cliente: " + clientes.getNome());
                    Toast.makeText(getApplicationContext(), "Salvou o Cliente: " + clientes.getNome(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Log.e("FormularioCliente", "Erro (" + response.code()+"): Verifique novamente os valores");
                    Toast.makeText(getApplicationContext(), "Erro (" + response.code()+"): Verifique novamente os valores", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Clientes> call, Throwable t) {
                Log.e("FormularioCliente", "Erro: " + t.getMessage());
            }
        });
    }

    private void atualizaCliente(Clientes clientes) {
        Call<Clientes> call;
        Log.i("FormularioCliente","Vai atualizar o cliente "+ clientes.getId());
        call = service.atualizaCliente(clientes.getId(), clientes);
        call.enqueue(new Callback<Clientes>() {
            @Override
            public void onResponse(Call<Clientes> call, Response<Clientes> response) {
                if (response.isSuccessful()) {
                    Log.i("FormularioCliente", "Atualizou o Cliente " + clientes.getId());
                    Toast.makeText(getApplicationContext(), "Atualizou o Cliente " + clientes.getId(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Log.e("FormularioCliente", "Erro (" + response.code()+"): Verifique novamente os valores");
                    Toast.makeText(getApplicationContext(), "Erro (" + response.code()+"): Verifique novamente os valores", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Clientes> call, Throwable t) {
                Log.e("FormularioCliente", "Erro: " + t.getMessage());
            }
        });
    }

    @NotNull
    private Clientes recuperaInformacoesFormulario() {
        EditText codigo = findViewById(R.id.editTextCodigo);
        EditText nome = findViewById(R.id.editTextNome);
        EditText cpf = findViewById(R.id.editTextCpf);
        EditText dtNascimento = findViewById(R.id.editTextDtNascimento);
        EditText email = findViewById(R.id.editTextEmail);
        EditText endereco = findViewById(R.id.editTextEndereco);
        EditText estadoCivil = findViewById(R.id.editTextEstadoCivil);
        EditText renda = findViewById(R.id.editTextRenda);
        EditText rg = findViewById(R.id.editTextRg);
        EditText sexo = findViewById(R.id.editTextSexo);
        Clientes clientes = new Clientes();
        clientes.setId(codigo.getText().toString());
        clientes.setNome(nome.getText().toString());
        clientes.setCpf(cpf.getText().toString());
      //  clientes.setDtNascimento(dtNascimento.getText().toString());
        clientes.setEmail(email.getText().toString());
        clientes.setEndereco(endereco.getText().toString());
      //  clientes.setRenda(renda.getText().toString()));
        clientes.setEstadoCivil(estadoCivil.getText().toString());
        clientes.setSexo(sexo.getText().toString());
        clientes.setRg(rg.getText().toString());
        return clientes;
    }
}