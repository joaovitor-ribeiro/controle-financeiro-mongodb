package controlefinanceiro.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controlefinanceiro.bean.PainelBean;
import controlefinanceiro.repository.DespesaRepository;
import controlefinanceiro.repository.GanhoRepository;

@Service
public class PainelService {

	@Autowired
	private GanhoRepository ganhoRepository;
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	public PainelBean painelControleFinanceiro(String data) throws ParseException {
		
		if (data == null || data.isEmpty()) {
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			String mes = (localDate.getMonthValue() < 10) ? "0" + localDate.getMonthValue() : localDate.getMonthValue() + "";
			localDate = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), 1);
			data = localDate.getYear() + "-" + mes + "-01";
		} else {
			data += "-01";
		}
		
		SimpleDateFormat formatoStr = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatoStr.parse(data);
		
		Double totalGanho   = ganhoRepository.getTotalValor(date);
		Double totalDespesa = despesaRepository.getTotalValor(date);
		
		totalDespesa = totalDespesa == null ? 0 : totalDespesa;
		totalGanho   = totalGanho   == null ? 0 : totalGanho;
		
		double saldo = totalGanho - totalDespesa;
		
		return new PainelBean(totalGanho, totalDespesa, saldo);
	}

}
