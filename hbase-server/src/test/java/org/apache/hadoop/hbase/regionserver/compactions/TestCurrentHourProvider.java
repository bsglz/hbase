/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.regionserver.compactions;

import static org.junit.Assert.assertEquals;
import org.apache.hadoop.hbase.HBaseClassTestRule;
import org.apache.hadoop.hbase.testclassification.RegionServerTests;
import org.apache.hadoop.hbase.testclassification.SmallTests;
import org.apache.hadoop.hbase.util.EnvironmentEdgeManager;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category({RegionServerTests.class, SmallTests.class})
public class TestCurrentHourProvider {

  @ClassRule
  public static final HBaseClassTestRule CLASS_RULE =
      HBaseClassTestRule.forClass(TestCurrentHourProvider.class);

  @Test
  public void testWithEnvironmentEdge() {
    // set 2020-08-20 11:52:41 should return 11
    EnvironmentEdgeManager.injectEdge(()->1597895561000L);
    CurrentHourProvider.forceUpdateTickForTest();
    assertEquals(11, CurrentHourProvider.getCurrentHour());
    // set 2020-08-20 15:04:00 should return 15
    EnvironmentEdgeManager.injectEdge(()->1597907081000L);
    CurrentHourProvider.forceUpdateTickForTest();
    assertEquals(15, CurrentHourProvider.getCurrentHour());
  }
}
