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

package org.nd4j.linalg.api.ops.impl.indexaccum;

import lombok.NonNull;
import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.complex.IComplexNumber;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseIndexAccumulation;
import org.nd4j.linalg.api.ops.Op;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.conditions.Condition;

import java.util.List;

/**
 * Calculate the index
 * of max value over a vector
 *
 * @author raver119@gmail.com
 */
public class FirstIndex extends BaseIndexAccumulation {
    protected Condition condition;
    protected double compare;
    protected double eps;
    protected int mode;

    public FirstIndex(SameDiff sameDiff, DifferentialFunction i_v, int[] dimensions, Condition condition, double compare, double eps, int mode) {
        super(sameDiff, i_v, dimensions);
        this.condition = condition;
        this.compare = compare;
        this.eps = eps;
        this.mode = mode;
    }

    public FirstIndex(SameDiff sameDiff, DifferentialFunction i_v, DifferentialFunction i_v2, int[] dimensions, Condition condition, double compare, double eps, int mode) {
        super(sameDiff, i_v, i_v2, dimensions);
        this.condition = condition;
        this.compare = compare;
        this.eps = eps;
        this.mode = mode;
    }

    public FirstIndex() {}


    public FirstIndex(INDArray x, @NonNull Condition condition) {
        this(x, condition, Nd4j.EPS_THRESHOLD);
    }

    public FirstIndex(INDArray x, @NonNull Condition condition, double eps) {
        super(x);

        this.condition = condition;
        this.compare = condition.getValue();
        this.mode = condition.condtionNum();
        this.eps = eps;


        this.extraArgs = new Object[] {compare, eps, (double) mode};
    }

    @Override
    public int update(double accum, int accumIdx, double x, int xIdx) {
        return (accum >= x ? accumIdx : xIdx);
    }

    @Override
    public int update(float accum, int accumIdx, float x, int xIdx) {
        return (accum >= x ? accumIdx : xIdx);
    }

    @Override
    public int update(double accum, int accumIdx, double x, double y, int idx) {
        return (accum >= x ? accumIdx : idx);
    }

    @Override
    public int update(float accum, int accumIdx, float x, float y, int idx) {
        return (accum >= x ? accumIdx : idx);
    }

    @Override
    public int update(IComplexNumber accum, int accumIdx, IComplexNumber x, int xIdx) {
        return (accum.absoluteValue().doubleValue() >= x.absoluteValue().doubleValue() ? accumIdx : xIdx);
    }

    @Override
    public int update(IComplexNumber accum, int accumIdx, double x, int idx) {
        return (accum.absoluteValue().doubleValue() >= x ? accumIdx : idx);
    }

    @Override
    public int update(IComplexNumber accum, int accumIdx, double x, double y, int idx) {
        return (accum.absoluteValue().doubleValue() >= x ? accumIdx : idx);
    }

    @Override
    public int update(IComplexNumber accum, int accumIdx, IComplexNumber x, IComplexNumber y, int idx) {
        return (accum.absoluteValue().doubleValue() >= x.absoluteValue().doubleValue() ? accumIdx : idx);
    }


    @Override
    public int opNum() {
        return 4;
    }

    @Override
    public String name() {
        return "first_index";
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, double other) {
        return origin;
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, float other) {
        return origin;
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, IComplexNumber other) {
        return origin;
    }

    @Override
    public float op(float origin, float other) {
        return origin;
    }

    @Override
    public double op(double origin, double other) {
        return origin;
    }

    @Override
    public double op(double origin) {
        return origin;
    }

    @Override
    public float op(float origin) {
        return origin;
    }

    @Override
    public IComplexNumber op(IComplexNumber origin) {
        return origin;
    }

    @Override
    public float zeroFloat() {
        return 0.0f;
    }

    @Override
    public float zeroHalf() {
        return zeroFloat();
    }

    @Override
    public double zeroDouble() {
        return 0.0;
    }

    @Override
    public IComplexNumber zeroComplex() {
        return Nd4j.createComplexNumber(-Double.MAX_VALUE, 0);
    }

    @Override
    public Op opForDimension(int index, int dimension) {
        INDArray xAlongDimension = x.vectorAlongDimension(index, dimension);

        return new FirstIndex(x.vectorAlongDimension(index, dimension), condition, eps);

    }

    @Override
    public Op opForDimension(int index, int... dimension) {
        INDArray xAlongDimension = x.tensorAlongDimension(index, dimension);

        return new FirstIndex(x.tensorAlongDimension(index, dimension), condition, eps);
    }

    @Override
    public List<DifferentialFunction> doDiff(List<DifferentialFunction> f1) {
        return null;
    }
}
