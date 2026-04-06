


package org.example.presentation.cli.commands.transaction.subCommands;

import java.util.List;

import org.example.application.dtos.transaction.TransactionCreateDto;
import org.example.application.services.TransactionService;
import org.example.presentation.cli.commands.transaction.dtos.TransactionInputDto;
import org.example.presentation.cli.commands.transaction.mappers.TransactionInputMapper;
import org.example.presentation.cli.commands.transaction.validation.AddTransactionValidation;
import org.example.presentation.cli.core.CommandContext;
import org.example.presentation.cli.core.interfaces.Command;
import org.example.presentation.cli.core.interfaces.ValidationRule;

public class AddTransaction implements Command {
	TransactionService service;

	public AddTransaction(TransactionService service){
		this.service = service;
	}


	@Override
	public String name() {
		return "add";
	}

	@Override
	public String info() {
		return """
				Create a new transaction.
				Usage: wallet transaction create --type INPUT --amount 10.50 --dateTime \"2026-04-04 10:00:00\" --description \"Coffee\" --installments 1
				""";
	}

	@Override
	public List<ValidationRule> specArgs(CommandContext context) {
		return List.of(AddTransactionValidation.INSTANCE);
	}

	@Override
	public void execute(CommandContext context) {
		TransactionInputDto input = new TransactionInputDto(
			context.get("type"),
			context.get("amount"),
			context.get("dateTime"),
			context.get("description"),
			context.get("installments"),
			context.get("category")
		);

		TransactionCreateDto dto = TransactionInputMapper.toCreateDto(input);

		this.service.insert(dto);

		System.out.println("Successful transaction creation!");
		System.out.println("Tipo: " + dto.type());
		System.out.println("Valor: " + dto.amount());
		System.out.println("Data/Hora: " + dto.dateTime());
		System.out.println("Descricao: " + dto.description());
		System.out.println("Parcelas: " + dto.installments());
		System.out.println("Categoria: " + dto.categoryName());
	}
	
}