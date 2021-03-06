/*!
 * @header{License}
 * @copyright Copyright (c) 2017-2018 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @header{Files}
 * @file base_drive_handler_factory.hpp
 */

#pragma once

#include "tools/drive_discovery/base_drive_handler.hpp"

#include <memory>

namespace agent {
namespace nvme {
namespace tools {

/*!
 * Interface for drive handler factories. Their responsibility is to provide proper drive handlers
 * depending on the drive capabilities.
 */
class BaseDriveHandlerFactory {
public:

    /*!
     * @brief virtual Destructor
     */
    virtual ~BaseDriveHandlerFactory();

    /*!
     * @brief Returns drive handler for a specified drive
     * @param name Name of the drive a handler is requested for
     * @return Drive handler shared pointer
     */
    virtual std::shared_ptr<BaseDriveHandler> get_handler(const std::string& name) const = 0;

};

}
}
}
