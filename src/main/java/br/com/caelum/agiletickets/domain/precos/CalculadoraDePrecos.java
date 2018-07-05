package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		Integer totalIngressos = sessao.getTotalIngressos();
		Integer ingressosReservados = sessao.getIngressosReservados();
		double percentualDeIngressos = (totalIngressos - ingressosReservados)
				/ totalIngressos.doubleValue();
		TipoDeEspetaculo tipoEspetaculo = sessao.getEspetaculo().getTipo();
		BigDecimal precoSessao = sessao.getPreco();
		
		/*switch (tipoEspetaculo) {
		case TipoDeEspetaculo.CINEMA:
		}*/
		if (tipoEspetaculo.equals(TipoDeEspetaculo.CINEMA)
				|| tipoEspetaculo.equals(TipoDeEspetaculo.SHOW)) {
			// quando estiver acabando os ingressos...
			if (percentualDeIngressos <= 0.05) {
				preco = precoSessao.add(precoSessao.multiply(BigDecimal
						.valueOf(0.10)));
			} else {
				preco = precoSessao;
			}
		} else if (tipoEspetaculo.equals(TipoDeEspetaculo.BALLET)) {
			preco = calculaPreco(percentualDeIngressos, precoSessao);

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = preco
						.add(precoSessao.multiply(BigDecimal.valueOf(0.10)));
			}
		} else if (tipoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = calculaPreco(percentualDeIngressos, precoSessao);

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = preco
						.add(precoSessao.multiply(BigDecimal.valueOf(0.10)));
			}
		} else {
			// nao aplica aumento para teatro (quem vai é pobretão)
			preco = precoSessao;
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPreco(double d, BigDecimal precoSessao) {
		BigDecimal preco;
		if (d <= 0.50) {
			preco = precoSessao.add(precoSessao.multiply(BigDecimal
					.valueOf(0.20)));
		} else {
			preco = precoSessao;
		}
		return preco;
	}

}