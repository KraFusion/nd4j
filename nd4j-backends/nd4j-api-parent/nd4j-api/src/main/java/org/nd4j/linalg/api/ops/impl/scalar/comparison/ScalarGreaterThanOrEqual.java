/*-
 *
 *  * Copyright 2015 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

package org.nd4j.linalg.api.ops.impl.scalar.comparison;

import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.complex.IComplexNumber;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseScalarOp;
import org.nd4j.linalg.api.ops.Op;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;
import java.util.List;

/**
 * Return a binary (0 or 1) when greater than or equal to a number
 *
 * @author Adam Gibson
 */
public class ScalarGreaterThanOrEqual extends BaseScalarOp {
    public ScalarGreaterThanOrEqual() {}

    public ScalarGreaterThanOrEqual(INDArray x, INDArray y, INDArray z, long n, Number num) {
        super(x, y, z, n, num);
    }

    public ScalarGreaterThanOrEqual(INDArray x, Number num) {
        super(x, num);
    }

    public ScalarGreaterThanOrEqual(INDArray x, INDArray y, INDArray z, long n, IComplexNumber num) {
        super(x, y, z, n, num);
    }

    public ScalarGreaterThanOrEqual(SameDiff sameDiff, DifferentialFunction i_v, Number scalar) {
        super(sameDiff, i_v, scalar);
    }

    public ScalarGreaterThanOrEqual(SameDiff sameDiff, DifferentialFunction i_v, Number scalar, boolean inPlace) {
        super(sameDiff, i_v, scalar, inPlace);
    }

    public ScalarGreaterThanOrEqual(SameDiff sameDiff, DifferentialFunction i_v, Number scalar, boolean inPlace, Object[] extraArgs) {
        super(sameDiff, i_v, scalar, inPlace, extraArgs);
    }

    public ScalarGreaterThanOrEqual(SameDiff sameDiff, DifferentialFunction i_v, Number scalar, Object[] extraArgs) {
        super(sameDiff, i_v, scalar, extraArgs);
    }

    public ScalarGreaterThanOrEqual(INDArray x, IComplexNumber num) {
        super(x, num);
    }

    @Override
    public int opNum() {
        return 16;
    }

    @Override
    public String name() {
        return "greaterthanorequal_scalar";
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, double other) {
        return origin.absoluteValue().doubleValue() >= num.doubleValue() ? Nd4j.createComplexNumber(1, 0)
                        : Nd4j.createComplexNumber(0, 0);

    }

    @Override
    public IComplexNumber op(IComplexNumber origin, float other) {
        return origin.absoluteValue().doubleValue() >= num.doubleValue() ? Nd4j.createComplexNumber(1, 0)
                        : Nd4j.createComplexNumber(0, 0);
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, IComplexNumber other) {
        return origin.absoluteValue().doubleValue() >= num.doubleValue() ? Nd4j.createComplexNumber(1, 0)
                        : Nd4j.createComplexNumber(0, 0);
    }

    @Override
    public float op(float origin, float other) {
        return origin >= num.floatValue() ? 1 : 0;

    }

    @Override
    public double op(double origin, double other) {
        return origin >= num.floatValue() ? 1 : 0;

    }

    @Override
    public double op(double origin) {
        return origin >= num.floatValue() ? 1 : 0;

    }

    @Override
    public float op(float origin) {
        return origin >= num.floatValue() ? 1 : 0;

    }

    @Override
    public IComplexNumber op(IComplexNumber origin) {
        return origin.absoluteValue().doubleValue() >= num.doubleValue() ? Nd4j.createComplexNumber(1, 0)
                        : Nd4j.createComplexNumber(0, 0);
    }

    @Override
    public Op opForDimension(int index, int dimension) {
        if (num != null)
            return new ScalarGreaterThanOrEqual(x.vectorAlongDimension(index, dimension), num);
        else
            return new ScalarGreaterThanOrEqual(x.vectorAlongDimension(index, dimension), complexNumber);
    }

    @Override
    public Op opForDimension(int index, int... dimension) {
        if (num != null)
            return new ScalarGreaterThanOrEqual(x.tensorAlongDimension(index, dimension), num);
        else
            return new ScalarGreaterThanOrEqual(x.tensorAlongDimension(index, dimension), complexNumber);
    }




    @Override
    public List<DifferentialFunction> doDiff(List<DifferentialFunction> f1) {
        return Arrays.<DifferentialFunction>asList(f().val(getResult()));
    }

}
