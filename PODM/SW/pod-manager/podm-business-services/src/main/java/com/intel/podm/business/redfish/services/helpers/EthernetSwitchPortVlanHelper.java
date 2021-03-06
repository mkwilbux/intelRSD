/*
 * Copyright (c) 2015-2018 Intel Corporation
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

package com.intel.podm.business.redfish.services.helpers;

import com.intel.podm.business.ContextResolvingException;
import com.intel.podm.business.EntityOperationException;
import com.intel.podm.business.RequestValidationException;
import com.intel.podm.business.entities.redfish.EthernetSwitchPort;
import com.intel.podm.business.entities.redfish.EthernetSwitchPortVlan;
import com.intel.podm.business.redfish.EntityTreeTraverser;
import com.intel.podm.business.services.context.Context;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static com.intel.podm.business.Violations.createWithViolations;
import static javax.transaction.Transactional.TxType.MANDATORY;

@Dependent
public class EthernetSwitchPortVlanHelper {
    @Inject
    private EntityTreeTraverser traverser;

    @Transactional(MANDATORY)
    public EthernetSwitchPortVlan validateAndGetVlan(EthernetSwitchPort switchPort, Context primaryVlan)
        throws EntityOperationException, RequestValidationException {
        if (primaryVlan == null) {
            return null;
        }
        EthernetSwitchPortVlan vlan = convertVlanContextToVlan(primaryVlan);
        verifyVlanToSwitchPortRelation(switchPort, vlan);

        return vlan;
    }

    private EthernetSwitchPortVlan convertVlanContextToVlan(Context vlanContext) throws RequestValidationException {
        try {
            return (EthernetSwitchPortVlan) traverser.traverse(vlanContext);
        } catch (ContextResolvingException e) {
            throw new RequestValidationException(createWithViolations("Provided VLAN was not found in specified context"));
        }
    }

    private void verifyVlanToSwitchPortRelation(EthernetSwitchPort switchPort, EthernetSwitchPortVlan vlan) throws EntityOperationException {
        if (!switchPort.equals(vlan.getEthernetSwitchPort())) {
            throw new EntityOperationException("Provided VLAN doesn't belong to proper switch port");
        }
    }
}
