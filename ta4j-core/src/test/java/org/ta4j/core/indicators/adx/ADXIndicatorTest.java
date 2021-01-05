/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 Marc de Verdelhan, 2017-2019 Ta4j Organization & respective
 * authors (see AUTHORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core.indicators.adx;

import org.junit.Test;
import org.ta4j.core.ExternalIndicatorTest;
import org.ta4j.core.Indicator;
import org.ta4j.core.TestUtils;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.AbstractIndicatorTest;
import org.ta4j.core.indicators.XLSIndicatorTest;
import org.ta4j.core.mocks.MockBar;
import org.ta4j.core.mocks.MockBarSeries;
import org.ta4j.core.num.Num;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.ta4j.core.TestUtils.assertIndicatorEquals;

public class ADXIndicatorTest extends AbstractIndicatorTest<BarSeries, Num> {

	protected BarSeries data;
    private ExternalIndicatorTest xls;

    public ADXIndicatorTest(Function<Number, Num> nf) throws Exception {
        super((data, params) -> new ADXIndicator((BarSeries) data, (int) params[0], (int) params[1]), nf);
        xls = new XLSIndicatorTest(this.getClass(), "ADX.xls", 15, numFunction);
    }

    @Test
    public void externalData() throws Exception {
        BarSeries series = xls.getSeries();
        Indicator<Num> actualIndicator;

        actualIndicator = getIndicator(series, 1, 1);
        assertIndicatorEquals(xls.getIndicator(1, 1), actualIndicator);
        assertEquals(100.0, actualIndicator.getValue(actualIndicator.getBarSeries().getEndIndex()).doubleValue(),
                TestUtils.GENERAL_OFFSET);

        actualIndicator = getIndicator(series, 3, 2);
        assertIndicatorEquals(xls.getIndicator(3, 2), actualIndicator);
        assertEquals(12.1330, actualIndicator.getValue(actualIndicator.getBarSeries().getEndIndex()).doubleValue(),
                TestUtils.GENERAL_OFFSET);

        actualIndicator = getIndicator(series, 13, 8);
        assertIndicatorEquals(xls.getIndicator(13, 8), actualIndicator);
        assertEquals(7.3884, actualIndicator.getValue(actualIndicator.getBarSeries().getEndIndex()).doubleValue(),
                TestUtils.GENERAL_OFFSET);
        
        
        // let's do some additional tests
        extraTests();

    }

	private void extraTests() {
		
		final List<Bar> bars = new ArrayList<>();

		bars.add(new MockBar(887.39, 899.59, 954.99, 850.1, numFunction));
		bars.add(new MockBar(897.8, 843.97, 914.84, 780.00, numFunction));
		bars.add(new MockBar(844.00, 826.67, 876.54, 790.43, numFunction));
		bars.add(new MockBar(827.9, 775.00, 834.65, 720.00, numFunction));
		bars.add(new MockBar(774.97, 785.2, 788.58, 720.00, numFunction));
		bars.add(new MockBar(784.45, 795.45, 798.47, 756.42, numFunction));
		bars.add(new MockBar(795.45, 828.75, 840.42, 777.93, numFunction));
		bars.add(new MockBar(830.09, 823.81, 830.37, 792.67, numFunction));
		bars.add(new MockBar(824.16, 847.56, 851.08, 815.04, numFunction));
		bars.add(new MockBar(849.85, 847.24, 862.18, 838.36, numFunction));
		bars.add(new MockBar(848.78, 824.63, 854, 00, 801.03, numFunction));
		bars.add(new MockBar(828.48, 814.16, 838.46, 803.19, numFunction));
		bars.add(new MockBar(814.5, 828.98, 837.34, 814.43, numFunction));
		bars.add(new MockBar(829.55, 852.11, 855.61, 828.1, numFunction));
		bars.add(new MockBar(851.13, 834.68, 857.61, 831.43, numFunction));
		bars.add(new MockBar(834.53, 823.77, 846.45, 810.67, numFunction));
		bars.add(new MockBar(823.17, 827.51, 835.85, 820.54, numFunction));
		bars.add(new MockBar(827.84, 850.67, 853.99, 817.89, numFunction));
		bars.add(new MockBar(851.67, 891.21, 925, 00, 851.67, numFunction));
		bars.add(new MockBar(892.15, 908.5, 910.00, 867.37, numFunction));
		data = new MockBarSeries(bars);

		ADXIndicator adx = new ADXIndicator(data, 14);
		for (int i = 0; i < bars.size(); i++) {
			Double val = adx.getValue(i).doubleValue();
			System.out.println("ADX i=" + i + ", val=" + val);
		}
		
	}

}
