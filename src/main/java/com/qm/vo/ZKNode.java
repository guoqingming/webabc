/**
 *
 * Copyright (c) 2014, Deem Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.qm.vo;

import java.util.ArrayList;
import java.util.List;

public class ZKNode {

    List<String> nodeLst;
    List<LeafBean> leafBeanLSt;

    public ZKNode() {
        nodeLst = new ArrayList<>();
        leafBeanLSt = new ArrayList<>();
    }

    public List<String> getNodeLst() {
        return nodeLst;
    }

    public void setNodeLst(List<String> nodeLst) {
        this.nodeLst = nodeLst;
    }

    public List<LeafBean> getLeafBeanLSt() {
        return leafBeanLSt;
    }

    public void setLeafBeanLSt(List<LeafBean> leafBeanLSt) {
        this.leafBeanLSt = leafBeanLSt;
    }

}