package lib;
private static final int BASE_TAX_EXEMPTION = 54000000;
private static final int MARRIED_ADDITIONAL_EXEMPTION = 4500000;
private static final int CHILD_ADDITIONAL_EXEMPTION = 4500000;
private static final int MAX_CHILDREN = 3;
private static final double TAX_RATE = 0.05;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
public static int calculateTax(TaxPayer taxPayer) {
    int tax = 0;

    if (taxPayer.getMonthsWorked() > 12) {
        System.err.println("More than 12 month working per year");
    }

    int numberOfChildren = taxPayer.getNumberOfChildren();
    if (numberOfChildren > MAX_CHILDREN) {
        numberOfChildren = MAX_CHILDREN;
    }

    int nonTaxableIncome = BASE_TAX_EXEMPTION;
    if (taxPayer.isMarried()) {
        nonTaxableIncome += MARRIED_ADDITIONAL_EXEMPTION + (numberOfChildren * CHILD_ADDITIONAL_EXEMPTION);
    }

    int grossIncome = (taxPayer.getMonthlySalary() + taxPayer.getOtherMonthlyIncome()) * taxPayer.getMonthsWorked();
    int netIncome = grossIncome - taxPayer.getAnnualDeductible();
    tax = (int) Math.round(TAX_RATE * (netIncome - nonTaxableIncome));

    return Math.max(tax, 0);
}

