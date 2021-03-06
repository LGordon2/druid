/*
 * Druid - a distributed column store.
 * Copyright 2012 - 2015 Metamarkets Group Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.druid.query.aggregation;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

/**
 */
public class LongSumAggregatorTest
{
  private void aggregate(TestLongColumnSelector selector, LongSumAggregator agg)
  {
    agg.aggregate();
    selector.increment();
  }

  @Test
  public void testAggregate()
  {
    final TestLongColumnSelector selector = new TestLongColumnSelector(new long[]{24L, 20L});
    LongSumAggregator agg = new LongSumAggregator("billy", selector);

    Assert.assertEquals("billy", agg.getName());

    Assert.assertEquals(0l, agg.get());
    Assert.assertEquals(0l, agg.get());
    Assert.assertEquals(0l, agg.get());
    aggregate(selector, agg);
    Assert.assertEquals(24l, agg.get());
    Assert.assertEquals(24l, agg.get());
    Assert.assertEquals(24l, agg.get());
    aggregate(selector, agg);
    Assert.assertEquals(44l, agg.get());
    Assert.assertEquals(44l, agg.get());
    Assert.assertEquals(44l, agg.get());
  }

  @Test
  public void testComparator()
  {
    final TestLongColumnSelector selector = new TestLongColumnSelector(new long[]{18293L});
    LongSumAggregator agg = new LongSumAggregator("billy", selector);

    Assert.assertEquals("billy", agg.getName());

    Object first = agg.get();
    agg.aggregate();

    Comparator comp = new LongSumAggregatorFactory("null", "null").getComparator();

    Assert.assertEquals(-1, comp.compare(first, agg.get()));
    Assert.assertEquals(0, comp.compare(first, first));
    Assert.assertEquals(0, comp.compare(agg.get(), agg.get()));
    Assert.assertEquals(1, comp.compare(agg.get(), first));
  }
}
