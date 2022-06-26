package br.unibh.sdm.apponlybank.entidades;

import android.text.Editable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Clientes implements Serializable {

    private String id;
    private String nome;
    private String cpf;
    private Date dtNascimento;
    private String email;
    private String endereco;
    private String estadoCivil;
    private Float renda;
    private String rg;
    private String sexo;

    public Clientes() {
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public Float getRenda() {
        return renda;
    }

    public String getRg() {
        return rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setId(String id) { this.id = id; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setdtNascimento(Editable dtNascimento) { this.dtNascimento = (Date) dtNascimento; }

    public void setEmail(String email) { this.email = email; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public void setRenda(Float renda) { this.renda = renda; }

    public void setRg(String rg) { this.rg = rg; }

    public void setSexo(String sexo) { this.sexo = sexo; }

    @Override
    public String toString() {
        return "Clientes{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dtNascimento=" + dtNascimento + '\'' +
                ", email=" + email + '\'' +
                ", endereco=" + endereco + '\'' +
                ", renda=" + renda + '\'' +
                ", rg=" + rg + '\'' +
                ", sexo=" + sexo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clientes)) return false;
        Clientes that = (Clientes) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getCpf(), that.getCpf()) &&
                Objects.equals(getDtNascimento(), that.getDtNascimento()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getEndereco(), that.getEndereco()) &&
                Objects.equals(getEstadoCivil(), that.getEstadoCivil()) &&
                Objects.equals(getRenda(), that.getRenda()) &&
                Objects.equals(getRg(), that.getRg()) &&
                Objects.equals(getSexo(), that.getSexo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCpf(), getDtNascimento(), getEmail(),
                getEndereco(), getEstadoCivil(), getRenda(), getRg(), getSexo());
    }
}
