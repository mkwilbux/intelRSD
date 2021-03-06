# <license_header>
#
# Copyright (c) 2017-2018 Intel Corporation
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# </license_header>

cmake_minimum_required(VERSION 2.8)
project("JSON RPC" CXX)

set(JSON_RPC_LIBRARIES
    json-rpc
    CACHE PATH "JSON RPC libraries"
    )

set(JSON_RPC_INCLUDE_DIRS
    ${CMAKE_CURRENT_SOURCE_DIR}/include
    CACHE PATH "JSON RPC include directory"
    )

include_directories(
    ${JSON_INCLUDE_DIRS}
    include
)

include_directories(SYSTEM
    ${CURL_INCLUDE_DIRS}
    )

add_library(json-rpc STATIC
    src/common.cpp
    src/connectors/abstract_server_connector.cpp
    src/connectors/abstract_client_connector.cpp
    src/connectors/http_client_connector.cpp
    src/connectors/http_server_connector.cpp
    src/handlers/abstract_request_handler.cpp
    src/handlers/abstract_request_invoker.cpp
    src/handlers/json_rpc_request_handler.cpp
    src/handlers/json_rpc_request_invoker.cpp
    src/json_rpc_error.cpp
    src/json_rpc_exception.cpp
    src/json_rpc_request.cpp
    src/json_rpc_response.cpp
    )

target_link_libraries(json-rpc
    ${JSON_LIBRARIES}
    ${MICROHTTPD_LIBRARIES}
    ${CURL_LIBRARIES}
    )

set_source_files_properties(
    src/connectors/http_client_connector.cpp
    PROPERTIES
    COMPILE_FLAGS "-Wno-disabled-macro-expansion"
)

set_source_files_properties(
    src/connectors/http_server_connector.cpp
    PROPERTIES
    COMPILE_FLAGS "-Wno-missing-braces -Wno-missing-field-initializers"
)

install(TARGETS json-rpc
    RUNTIME DESTINATION bin
    LIBRARY DESTINATION lib
    ARCHIVE DESTINATION lib
    )

add_subdirectory(tests)
