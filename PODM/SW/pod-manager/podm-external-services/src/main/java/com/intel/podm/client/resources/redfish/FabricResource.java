/*
 * Copyright (c) 2016-2018 Intel Corporation
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

package com.intel.podm.client.resources.redfish;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intel.podm.client.LinkName;
import com.intel.podm.client.OdataTypes;
import com.intel.podm.client.WebClientRequestException;
import com.intel.podm.client.reader.ResourceSupplier;
import com.intel.podm.client.resources.ExternalServiceResource;
import com.intel.podm.client.resources.ExternalServiceResourceImpl;
import com.intel.podm.client.resources.ODataId;
import com.intel.podm.common.types.Protocol;
import com.intel.podm.common.types.Status;

import static com.intel.podm.common.types.redfish.OdataTypeVersions.VERSION_PATTERN;

@OdataTypes({
    "#Fabric" + VERSION_PATTERN + "Fabric"
})
public class FabricResource extends ExternalServiceResourceImpl implements ExternalServiceResource {
    @JsonProperty("FabricType")
    private Protocol fabricType;

    @JsonProperty("MaxZones")
    private Integer maxZones;

    @JsonProperty("Status")
    private Status status;

    @JsonProperty("Zones")
    private ODataId zones;

    @JsonProperty("Endpoints")
    private ODataId endpoints;

    @JsonProperty("Switches")
    private ODataId switches;

    public Protocol getFabricType() {
        return fabricType;
    }

    public Integer getMaxZones() {
        return maxZones;
    }

    public Status getStatus() {
        return status;
    }

    @LinkName("zoneInFabric")
    public Iterable<ResourceSupplier> getZones() throws WebClientRequestException {
        return processMembersListResource(zones);
    }

    @LinkName("endpointInFabric")
    public Iterable<ResourceSupplier> getEndpoints() throws WebClientRequestException {
        return processMembersListResource(endpoints);
    }

    @LinkName("switchInFabric")
    public Iterable<ResourceSupplier> getSwitches() throws WebClientRequestException {
        return processMembersListResource(switches);
    }
}
