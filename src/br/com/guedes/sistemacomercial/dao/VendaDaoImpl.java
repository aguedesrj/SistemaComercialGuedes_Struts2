package br.com.guedes.sistemacomercial.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemacomercial.model.ItensVenda;
import br.com.guedes.sistemacomercial.model.VW_Venda;
import br.com.guedes.sistemacomercial.model.Venda;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;
import br.com.guedes.sistemacomercial.util.Util;

/**
 * 
 * @author AndréLessa
 *
 */
public class VendaDaoImpl extends HibernateDaoSupport implements VendaDao {

	private static final Logger LOGGER = Logger.getLogger(VendaDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.VendaDao#pesquisarVendaPorCriterios(br.com.guedes.sistemacomercial.model.Venda)
	 */
	@SuppressWarnings("unchecked")
	public List<VW_Venda> pesquisarVendaPorCriterios(final Venda venda) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Pesquisando Vendas...");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from VW_Venda where venCodigo > 0 ");
			
			// Codigo
			if (venda.getVenCodigo() != null) {
				hql.append(" and proCodigo = " + venda.getVenCodigo());
			}
			
			List<VW_Venda> lista = getHibernateTemplate().findByValueBean(hql.toString(), new VW_Venda());
			if (lista == null || lista.isEmpty()) {
				throw new BusinessException("Nenhuma venda encontrada.");
			}
			return lista;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Produto.", e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e);
			}
			throw new IntegrationException("Erro ao pesquisar Produto.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.VendaDao#obterVendasPorPeriodo(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ItensVenda> obterVendasPorPeriodo(final String dataInicio, final String dataFim) throws BusinessException, IntegrationException {
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("select count(v.ven_codigo), ");
			sql.append("       extract(day from v.ven_datafim)||'/'||extract(month from v.ven_datafim)||'/'||extract(year from v.ven_datafim) || ' 00:00:00' ");
			sql.append("  from tbl_venda v ");
			sql.append(" where v.ven_datafim between '" + dataInicio.replace('/', '.') + "' and '" + dataFim.replace('/', '.') + "' ");
			sql.append("group by extract(day from v.ven_datafim)||'/'||extract(month from v.ven_datafim)||'/'||extract(year from v.ven_datafim) ");
			
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			if (query == null || query.list().isEmpty()) {
				throw new BusinessException("Nenhuma venda encontrada.");
			}
			
			List<ItensVenda> listaRetorno = new ArrayList<ItensVenda>();
			List<Object[]> entitys = query.list();
			for (Object[] entity : entitys) {
				ItensVenda itensVenda = new ItensVenda();
				Venda venda = new Venda();
				
				// seta valores.
				itensVenda.setItvQuantidade((Integer) entity[0]);
				venda.setVenDataFim(Util.converterStringParaCalendar((String) entity[1]));
				itensVenda.setVenda(venda);
				
				listaRetorno.add(itensVenda);
			}
			return listaRetorno;
		} catch (Exception e) {
			LOGGER.error("Erro ao obter vendas por período.", e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			}
			throw new IntegrationException("Erro ao obter vendas por período.", e);
		}
	}
}
