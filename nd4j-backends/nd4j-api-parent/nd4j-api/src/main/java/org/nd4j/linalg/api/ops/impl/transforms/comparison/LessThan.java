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

package org.nd4j.linalg.api.ops.impl.transforms.comparison;

import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.complex.IComplexNumber;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseTransformOp;
import org.nd4j.linalg.api.ops.Op;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;
import java.util.List;

/**
 * Bit mask over the ndarrays as to whether
 * the components are less than or not
 *
 * @author Adam Gibson
 */
public class LessThan extends BaseTransformOp {
    public LessThan(SameDiff sameDiff, DifferentialFunction i_v1, DifferentialFunction i_v2) {
        super(sameDiff, i_v1, i_v2);
    }

    public LessThan(SameDiff sameDiff, DifferentialFunction i_v1, DifferentialFunction i_v2, boolean inPlace) {
        super(sameDiff, i_v1, i_v2, inPlace);
    }

    public LessThan(SameDiff sameDiff) {
        super(sameDiff);
    }

    public LessThan(SameDiff sameDiff, DifferentialFunction i_v1, DifferentialFunction i_v2, Object[] extraArgs) {
        super(sameDiff, i_v1, i_v2, extraArgs);
    }

    public LessThan(SameDiff sameDiff, DifferentialFunction i_v, boolean inPlace) {
        super(sameDiff, i_v, inPlace);
    }

    public LessThan(SameDiff sameDiff, DifferentialFunction i_v, int[] shape, boolean inPlace, Object[] extraArgs) {
        super(sameDiff, i_v, shape, inPlace, extraArgs);
    }

    public LessThan(SameDiff sameDiff, DifferentialFunction i_v, Object[] extraArgs) {
        super(sameDiff, i_v, extraArgs);
    }

    public LessThan() {}

    public LessThan(INDArray x, INDArray y, INDArray z, long n) {
        super(x, y, z, n);
    }

    public LessThan(INDArray x) {
        super(x);
    }

    public LessThan(INDArray ndArray, INDArray dup) {
        super(ndArray, dup);
    }

    public LessThan(INDArray x, INDArray z, long n) {
        super(x, z, n);
    }

    @Override
    public int opNum() {
        return 5;
    }

    @Override
    public String name() {
        return "lt";
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, double other) {
        if (origin.isReal())
            return origin.realComponent().doubleValue() < other ? Nd4j.createComplexNumber(1.0, 0.0)
                            : Nd4j.createComplexNumber(0.0, 0.0);
        return Nd4j.createComplexNumber(0.0, 0.0);
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, float other) {
        if (origin.isReal())
            return origin.realComponent().doubleValue() < other ? Nd4j.createComplexNumber(1.0, 0.0)
                            : Nd4j.createComplexNumber(0.0, 0.0);
        return Nd4j.createComplexNumber(0.0, 0.0);
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, IComplexNumber other) {
        return origin.lt(other).eq(Nd4j.createComplexNumber(1.0, 0.0)) ? Nd4j.createComplexNumber(1.0, 0.0)
                        : Nd4j.createComplexNumber(0.0, 0.0);
    }

    @Override
    public float op(float origin, float other) {
        return origin < other ? 1.0f : 0.0f;
    }

    @Override
    public double op(double origin, double other) {
        return origin < other ? 1.0 : 0.0;
    }

    @Override
    public double op(double origin) {
        return 1;
    }

    @Override
    public float op(float origin) {
        return 1;
    }

    @Override
    public IComplexNumber op(IComplexNumber origin) {
        return Nd4j.createComplexNumber(1.0, 0.0);
    }

    @Override
    public Op opForDimension(int index, int dimension) {
        INDArray xAlongDimension = x.vectorAlongDimension(index, dimension);

        if (y() != null)
            return new LessThan(xAlongDimension, y.vectorAlongDimension(index, dimension),
                            z.vectorAlongDimension(index, dimension), xAlongDimension.length());
        else
            return new LessThan(xAlongDimension, z.vectorAlongDimension(index, dimension), xAlongDimension.length());

    }

    @Override
    public Op opForDimension(int index, int... dimension) {
        INDArray xAlongDimension = x.tensorAlongDimension(index, dimension);

        if (y() != null)
            return new LessThan(xAlongDimension, y.tensorAlongDimension(index, dimension),
                            z.tensorAlongDimension(index, dimension), xAlongDimension.length());
        else
            return new LessThan(xAlongDimension, z.tensorAlongDimension(index, dimension), xAlongDimension.length());

    }


    @Override
    public List<DifferentialFunction> doDiff(List<DifferentialFunction> f1) {
        return Arrays.<DifferentialFunction>asList(f().val(getResult()));
    }
}
