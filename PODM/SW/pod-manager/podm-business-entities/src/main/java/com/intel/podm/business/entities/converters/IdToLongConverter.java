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

package com.intel.podm.business.entities.converters;

import com.intel.podm.common.types.Id;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.intel.podm.common.types.Id.id;

@Converter(autoApply = false)
public class IdToLongConverter implements AttributeConverter<Id, Long> {
    @Override
    public Long convertToDatabaseColumn(Id value) {
        return value == null ? null : Long.valueOf(value.getValue());
    }

    @Override
    public Id convertToEntityAttribute(Long value) {
        return value == null ? null : id(value);
    }
}
