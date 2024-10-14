package nl.pancompany.unicorn.context.unicorn.adapter;

import lombok.Getter;
import nl.pancompany.unicorn.adapter.unicorn.out.financialhealthadapter.FinancialHealthAdapter;
import nl.pancompany.unicorn.adapter.unicorn.out.financialhealthadapter.FinancialHealthMapper;
import nl.pancompany.unicorn.adapter.unicorn.out.financialhealthadapter.CalculateSalesPort;
import nl.pancompany.unicorn.application.unicorn.port.out.GetFinancialHealthPort;

@Getter
public class FinanceAdapterContext {

    GetFinancialHealthPort getFinancialHealthPort;

    public FinanceAdapterContext(CalculateSalesPort calculateSalesPort) {
        this.getFinancialHealthPort = new FinancialHealthAdapter(calculateSalesPort, FinancialHealthMapper.INSTANCE);
    }
}
