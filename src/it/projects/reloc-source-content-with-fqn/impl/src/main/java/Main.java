/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.List;

public class Main
{
    public static relocated.StringMessage getStringMessage( CharSequence s )
    {
        relocated.StringMessage msg;
        if (s instanceof String)
        {
            msg = new relocated.StringMessage( (String) s );
        }
        else if (s == null) {
            msg = relocated.StringMessage.EMPTY;
        }
        else
        {
            msg = relocated.StringMessage
                    .from( s );
        }
        return msg;
    }

    public static relocated.StringMessage[] toStringMessageArray( List<relocated.Message> messages )
    {
        return messages.stream()
                .map( m ->
                {
                    if (m instanceof relocated.StringMessage)
                    {
                        return (relocated.StringMessage) m;
                    }
                    else
                    {
                        return getStringMessage( m.asString() );
                    }
                } ).toArray( relocated.StringMessage[]::new );
    }


    public static class MyMessage extends relocated.StringMessage implements relocated.Message
    {
        public MyMessage( String s ) throws relocated.MessageException
        {
            super(s);
        }
    }
}
