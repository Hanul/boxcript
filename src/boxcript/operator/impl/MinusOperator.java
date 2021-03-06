package boxcript.operator.impl;

import java.math.BigDecimal;

import boxcript.box.Box;
import boxcript.box.BoxUtil;
import boxcript.box.NativeValueBox;
import boxcript.operator.TwoParamOperator;

public class MinusOperator implements TwoParamOperator {
	private String operatorString = "-";	@Override public String getOperatorString() { return operatorString; }
	private int orderNum;					@Override public int getOrderNum() { return orderNum; }
	public MinusOperator(int orderNum) {
		this.orderNum = orderNum;
	}
	@Override
	public Box run(Box param1, Box param2) {
		if (param1.getOriginBox() instanceof NativeValueBox && param2.getOriginBox() instanceof NativeValueBox) {
			Object object1 = ((NativeValueBox) param1.getOriginBox()).getObject();
			Object object2 = ((NativeValueBox) param2.getOriginBox()).getObject();
			if (object1 instanceof BigDecimal && object2 instanceof BigDecimal)	return BoxUtil.createNativeBox(((BigDecimal) object1).subtract((BigDecimal) object2), "box/native/Number.box", param1.getMomBox(), param1.getLn(), param1.getCol()).init();
			else throw new Error();
		}
		if (param1.getOriginBox().getInnerBox("minus") != null)			return param1.getOriginBox().getInnerBox("minus").run(param2.getOriginBox());
		else if (param1.getOriginBox().getInnerBox("subtract") != null)	return param1.getOriginBox().getInnerBox("subtract").run(param2.getOriginBox());
		else throw new Error();
	}
}
