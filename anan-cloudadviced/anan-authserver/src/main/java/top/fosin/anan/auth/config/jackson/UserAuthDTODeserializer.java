/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.fosin.anan.auth.config.jackson;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import top.fosin.anan.cloudresource.entity.res.UserAuthDTO;

import java.io.IOException;

/**
 *
 */
class UserAuthDTODeserializer extends JsonDeserializer<UserAuthDTO> {

    @Override
    public UserAuthDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);

        UserAuthDTO result = new UserAuthDTO();
        result.setId(readJsonNode(jsonNode, "id").asLong());
        result.setTopId(readJsonNode(jsonNode, "topId").asLong());
        result.setOrganizId(readJsonNode(jsonNode, "organizId").asLong());
        result.setUsercode(readJsonNode(jsonNode, "usercode").asText());
        result.setUsername(readJsonNode(jsonNode, "username").asText());
        result.setNickname(readJsonNode(jsonNode, "nickname").asText());
        result.setFamilyName(readJsonNode(jsonNode, "familyName").asText());
        result.setMiddleName(readJsonNode(jsonNode, "middleName").asText());
        result.setGivenName(readJsonNode(jsonNode, "givenName").asText());
        result.setPreferredUsername(readJsonNode(jsonNode, "preferredUsername").asText());
        result.setWebsite(readJsonNode(jsonNode, "password").asText());
        result.setRealNameVerified((byte) readJsonNode(jsonNode, "realNameVerified").asInt());
        result.setPassword(readJsonNode(jsonNode, "password").asText());
        result.setBirthday(DateUtil.parse(readJsonNode(jsonNode, "birthday").asText()));
        result.setSex((byte) readJsonNode(jsonNode, "sex").asInt());
        result.setEmail(readJsonNode(jsonNode, "email").asText());
        result.setEmailVerified((byte) readJsonNode(jsonNode, "emailVerified").asInt());
        result.setPhone(readJsonNode(jsonNode, "phone").asText());
        result.setPhoneVerified((byte) readJsonNode(jsonNode, "phoneVerified").asInt());
        result.setStatus((byte) readJsonNode(jsonNode, "status").asInt());
        result.setAvatar(readJsonNode(jsonNode, "password").asText());
        result.setExpireTime(DateUtil.parse(readJsonNode(jsonNode, "expireTime").asText()));

        return result;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}
