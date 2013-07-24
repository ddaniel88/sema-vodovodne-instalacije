package enums;

import figures.*;

public enum FigureTypes {
//	E_SPN(E_Spn.class.getSimpleName()),
//	F_S(F_S.class.getSimpleName()),
//	MMA_ONP(Mma_Onp.class.getSimpleName()),
//	MMB_ON(Mmb_On.class.getSimpleName()),
//	MMK_LN(Mmk_Ln.class.getSimpleName()),
//	MMQ_LN(Mmq_Ln.class.getSimpleName()),
//	MMR_RN(Mmr_Rn.class.getSimpleName()),
//	P_ZN(P_Zn.class.getSimpleName()),
//	U_SN(U_Sn.class.getSimpleName());
	
	E_SPN(E_Spn.class.getName()),
	F_S(F_S.class.getName()),
	MMA_ONP(Mma_Onp.class.getName()),
	MMB_ON(Mmb_On.class.getName()),
	MMK_LN(Mmk_Ln.class.getName()),
	MMQ_LN(Mmq_Ln.class.getName()),
	MMR_RN(Mmr_Rn.class.getName()),
	P_ZN(P_Zn.class.getName()),
	U_SN(U_Sn.class.getName());

	private String code;

	private FigureTypes(String c) {
		code = c;
	}

	public String getCode() {
		return code;
	}
}
