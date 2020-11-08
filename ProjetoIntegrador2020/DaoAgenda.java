/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloConection.ConexaoBD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeans.BeansAgenda;

/**
 *
 * @author Daniel
 */
public class DaoAgenda {
    BeansAgenda agenda = new BeansAgenda();
    ConexaoBD conex = new ConexaoBD();
    
    ConexaoBD conexfuncionario = new ConexaoBD();
    ConexaoBD conexVeiculo = new ConexaoBD();
    
    int codfuncionario;
    int codveiculo;
    
    public void Salvar(BeansAgenda agenda){
        BuscarFuncionario(agenda.getNomeFuncionario());
        BuscarVeiculo(agenda.getNomeVeiculo());
        conex.conexao();
        try {
           PreparedStatement pst = conex.con.prepareStatement("insert into agenda (agenda_codfuncionario, agenda_codveiculo,agenda_data,agenda_estado,agenda_local,agenda_carga,agenda_status) values(?,?,?,?,?,?,?)");
        pst.setInt(1, codfuncionario);
        pst.setInt(2, codveiculo);
        pst.setDate(3, new java.sql.Date(agenda.getData().getTime()));
        pst.setString(4, agenda.getEstado());
        pst.setString(5, agenda.getLocalobservacao());
        pst.setString(6, agenda.getCarga());
        pst.setString(7, agenda.getStatus());
        
        pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"erro ao salvar agendamento");
        }
        
        conex.desconecta();
        
        
    }
    public void BuscarFuncionario(String nomeFuncionario){
        conexfuncionario.conexao();
        
        conexfuncionario.executasql("select *from funcionarios where nome_funcionario='" +nomeFuncionario +"'");
        try {
            conexfuncionario.rs.first();
            codfuncionario = conexfuncionario.rs.getInt("cod_usuario");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "funcionario nao encontrado");
        }
        
        conexfuncionario.desconecta();
        
    }
    public void BuscarVeiculo(String nomeVeiculo){
         conexVeiculo.conexao();
        
        conexVeiculo.executasql("select *from veiculos where marca_veiculo='" +nomeVeiculo +"'");
        try {
            conexVeiculo.rs.first();
            codveiculo = conexVeiculo.rs.getInt("cod_id");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "funcionario nao encontrado");
        }
        
        conexVeiculo.desconecta();
        
    }
        
    }

