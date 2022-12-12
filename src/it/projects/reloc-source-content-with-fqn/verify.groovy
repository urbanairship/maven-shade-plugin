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

def sourceJarFile = new java.util.jar.JarFile( new File( basedir, "uber/target/test-uber-1.0-sources.jar" ) )
try
{
    assert null != sourceJarFile.getJarEntry( "hidden/Message.java" )
    assert null != sourceJarFile.getJarEntry( "hidden/MessageException.java" )
    assert null != sourceJarFile.getJarEntry( "hidden/StringMessage.java" )
    assert null != sourceJarFile.getJarEntry( "Main.java" )

    def is = sourceJarFile.getInputStream(sourceJarFile.getJarEntry( "Main.java" ))

    def content = is.text;
    assert false == content.contains( 'relocated.');
    assert content.contains( 'hidden.Message');
    assert content.contains( 'hidden.MessageException');
    assert content.contains( 'hidden.StringMessage');
}
finally
{
    sourceJarFile.close()
}
